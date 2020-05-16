package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class QuestionDtoDaoImpl extends ReadWriteDaoImpl<QuestionDto, Long> implements QuestionDtoDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionDtoList() {
        List<QuestionDto> questionDto = new ArrayList<>();
        try {
            questionDto = entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.fullName, " +
                    "q.user.reputationCount, " +
                    "q.viewCount, " +
                    "q.countValuable, " +
                    "q.persistDateTime,  " +
                    "t.id, " +
                    "t.name, " +
                    "t.description, " +
                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                    "(SELECT a.isHelpful FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q JOIN q.tags t")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            TagDto tagDto = TagDto.builder()
                                    .id((Long) objects[7])
                                    .name((String) objects[8])
                                    .description((String) objects[9])
                                    .build();
                            List<TagDto> tagDtoList = new ArrayList<>();
                            tagDtoList.add(tagDto);
                            return QuestionDto.builder()
                                    .id((Long) objects[0])
                                    .title((String) objects[1])
                                    .username((String) objects[2])
                                    .reputationCount((Integer) objects[3])
                                    .viewCount((Integer) objects[4])
                                    .countValuable((Integer) objects[5])
                                    .persistDateTime((LocalDateTime) objects[6])
                                    .tags(tagDtoList)
                                    .countAnswer(((Number) objects[10]).intValue())
                                    .isHelpful((Boolean) objects[11])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            Map<Long, QuestionDto> result = new LinkedHashMap<>();
                            for (Object obj : list) {
                                QuestionDto questionDto = (QuestionDto) obj;
                                if (result.containsKey(questionDto.getId())) {
                                    result.get(questionDto.getId()).getTags().addAll(questionDto.getTags());
                                } else {
                                    result.put(questionDto.getId(), questionDto);
                                }
                            }
                            return new ArrayList<>(result.values());
                        }
                    })
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Long, List<QuestionDto>> getPaginationQuestion(int page, int size) {
        Map<Long, List<QuestionDto>> result = new HashMap<>();
        List<QuestionDto> listOfQuestionDto = new ArrayList<>();
        Long record = Long.parseLong(entityManager.createNativeQuery("SELECT COUNT(*) FROM question")
                .getSingleResult()
                .toString());
        String query = "SELECT " +
                "q.id, " +
                "q.title, " +
                "q.view_count, " +
                "(SELECT COUNT(*) FROM answer a WHERE a.question_id = q.id), " +
                "(SELECT a.is_helpful FROM answer a WHERE a.is_helpful = 1 AND a.question_id = q.id) " +
                "FROM question q";
        Query nativeQuery = (Query) entityManager.createNativeQuery(query);
        nativeQuery.setFirstResult((page - 1) * size);
        nativeQuery.setMaxResults(size);

        final List<Object[]> listFromEM = nativeQuery.getResultList();
        listFromEM.forEach(obj -> listOfQuestionDto.add(QuestionDto.builder()
                .id(new BigInteger(obj[0].toString()).longValue())
                .title((String) obj[1])
                .viewCount(Integer.parseInt(obj[2].toString()))
                .countAnswer(Integer.parseInt(obj[3].toString()))
                .isHelpful(Boolean.parseBoolean(obj[4] != null ? obj[4].toString() : "false"))
                .tags(getTag(new BigInteger(obj[0].toString()).longValue()))
                .lastAnswerNameAndDate(getLastAnswerNameAndDate(new BigInteger(obj[0].toString()).longValue()))
                .build()));
        result.put(record, listOfQuestionDto);
        return result;
    }

    @SuppressWarnings("unchecked")
    private List<TagDto> getTag(long q_id) {
        List<TagDto> result = new ArrayList<>();
        Query tagsQuery = (Query) entityManager.createNativeQuery("SELECT t.id, t.name, t.description " +
                "FROM question q " +
                "INNER JOIN question_has_tag qht " +
                    "ON qht.question_id = q.id " +
                "INNER JOIN tag t " +
                    "ON t.id = qht.tag_id " +
                "WHERE q.id = :q_id");
        final List<Object[]> listFromEM = tagsQuery.setParameter("q_id", q_id).getResultList();
        listFromEM.forEach(obj -> result.add(TagDto.builder()
                .id(new BigInteger(obj[0].toString()).longValue())
                .name(obj[1].toString())
                .description(obj[2].toString())
                .build()));
        return result;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getLastAnswerNameAndDate(long q_id) {
        Map<String, String> result = new HashMap<>();
        List<Object[]> listFromEM = entityManager.createNativeQuery("SELECT u.full_name, a.date_accept_time " +
                "FROM users u " +
                "INNER JOIN answer a " +
                    "ON u.id = a.user_id " +
                "INNER JOIN question q " +
                    "ON a.question_id = q.id " +
                "WHERE a.date_accept_time=(SELECT MAX(a.date_accept_time) from answer) " +
                    "AND q.id = :q_id " +
                "LIMIT 1").setParameter("q_id", q_id).getResultList();
        listFromEM.forEach(obj -> result.put(obj[0].toString(), obj[1].toString()));
        return result;
    }

}