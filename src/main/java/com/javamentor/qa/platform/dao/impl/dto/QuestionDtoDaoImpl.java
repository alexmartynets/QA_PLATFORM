package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.Role;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class QuestionDtoDaoImpl extends ReadWriteDAOImpl<QuestionDto, Long> implements QuestionDtoDao {

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
                    "q.description," +
                    "t.id, " +
                    "t.name, " +
                    "t.description " +
//                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
//                    "(SELECT a.isHelpful FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q JOIN q.tags t")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            TagDto tagDto = TagDto.builder()
                                    .id((Long) objects[8])
                                    .name((String) objects[9])
                                    .description((String) objects[10])
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
                                    .description((String) objects[7])
                                    .tags(tagDtoList)
//                                    .countAnswer(((Number) objects[10]).intValue())
//                                    .isHelpful((Boolean) objects[11])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            Map<Long, QuestionDto> result = new TreeMap<>(Comparator.reverseOrder());
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
    //todo настроить
    @Override
    public QuestionDto getQuestionDtoById(Long id) {
        QuestionDto questionDto = null;
        try {
            questionDto = (QuestionDto) entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.fullName, " +
                    "q.user.reputationCount, " +
                    "q.viewCount, " +
                    "q.countValuable, " +
                    "q.persistDateTime, " +
                    "q.description," +
                    "t.id, " +
                    "t.name, " +
                    "t.description " +
//                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
//                    "(SELECT a.isHelpful FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q JOIN q.tags t WHERE q.id = " + id)
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {

                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            TagDto tagDto = TagDto.builder()
                                    .id((Long) objects[8])
                                    .name((String) objects[9])
                                    .description((String) objects[10])
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
                                    .description((String) objects[7])
                                    .tags(tagDtoList)
//                                    .countAnswer(((Number) objects[10]).intValue())
//                                    .isHelpful((Boolean) objects[11])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            Map<Long, QuestionDto> result = new TreeMap<>(Comparator.reverseOrder());
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
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionDto;
    }
}