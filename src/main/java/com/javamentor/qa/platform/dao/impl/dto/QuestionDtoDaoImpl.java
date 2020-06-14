package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
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
                    "q.user.id, " +
                    "q.user.fullName, " +
                    "q.user.reputationCount, " +
                    "q.user.imageUser, " +
                    "q.viewCount, " +
                    "q.countValuable, " +
                    "q.persistDateTime,  " +
                    "t.id, " +
                    "t.name, " +
                    "t.description, " +
                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                    "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q JOIN q.tags t")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            UserDto userDto = UserDto.builder()
                                    .id((Long) objects[2])
                                    .fullName((String) objects[3])
                                    .reputationCount((Integer) objects[4])
                                    .imageUser((byte[]) objects[5])
                                    .build();
                            TagDto tagDto = TagDto.builder()
                                    .id((Long) objects[9])
                                    .name((String) objects[10])
                                    .description((String) objects[11])
                                    .build();
                            List<TagDto> tagDtoList = new ArrayList<>();
                            tagDtoList.add(tagDto);
                            return QuestionDto.builder()
                                    .id((Long) objects[0])
                                    .title((String) objects[1])
                                    .userDto(userDto)
                                    .viewCount((Integer) objects[6])
                                    .countValuable((Integer) objects[7])
                                    .persistDateTime((LocalDateTime) objects[8])
                                    .tags(tagDtoList)
                                    .countAnswer(((Number) objects[12]).intValue())
                                    .isHelpful((Boolean) objects[13])
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

    @SuppressWarnings("unchecked")
    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.title, " +
                "q.user.id, " +
                "q.user.fullName, " +
                "q.user.reputationCount, " +
                "q.user.imageUser, " +
                "q.viewCount, " +
                "q.countValuable, " +
                "q.persistDateTime, " +
                "q.lastUpdateDateTime, " +
                "q.description, " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id)," +
                "(SELECT a.user.fullName FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)), " +
                "(SELECT a.persistDateTime FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)) " +
                "FROM Question q JOIN q.tags t WHERE q.id =: id ")
                .unwrap(Query.class)
                .setParameter("id", id)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        UserDto userDto = UserDto.builder()
                                .id(((Number) objects[2]).longValue())
                                .fullName((String) objects[3])
                                .reputationCount((Integer) objects[4])
                                .imageUser((byte[]) objects[5])
                                .build();
                        TagDto tagDto = TagDto.builder()
                                .id((Long) objects[11])
                                .name((String) objects[12])
                                .description((String) objects[13])
                                .build();
                        List<TagDto> tagDtoList = new ArrayList<>();
                        tagDtoList.add(tagDto);
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .title((String) objects[1])
                                .userDto(userDto)
                                .viewCount((Integer) objects[6])
                                .countValuable((Integer) objects[7])
                                .persistDateTime((LocalDateTime) objects[8])
                                .lastUpdateDateTime((LocalDateTime) objects[9])
                                .description((String) objects[10])
                                .tags(tagDtoList)
                                .countAnswer(((Number) objects[14]).intValue())
                                .isHelpful((Boolean) objects[15])
                                .lastAnswerDate((LocalDateTime) objects[17])
                                .lastAnswerName((String) objects[16])
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
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionDtoListByUserId(Long userId) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        try {
            questionDtoList = entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.id, " +
                    "q.viewCount, " +
                    "q.countValuable, " +
                    "q.persistDateTime, " +
                    "q.lastUpdateDateTime, " +
                    "q.description," +
                    "t.id, " +
                    "t.name, " +
                    "t.description, " +
                    "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id)" +
                    "FROM Question q JOIN q.tags t WHERE q.user.id =: id ")
                    .unwrap(Query.class)
                    .setParameter("id", userId)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            UserDto userDto = UserDto.builder()
                                    .id((Long) objects[2])
                                    .build();
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
                                    .userDto(userDto)
                                    .viewCount((Integer) objects[3])
                                    .countValuable((Integer) objects[4])
                                    .persistDateTime((LocalDateTime) objects[5])
                                    .lastUpdateDateTime((LocalDateTime) objects[6])
                                    .description((String) objects[7])
                                    .tags(tagDtoList)
                                    .isHelpful((Boolean) objects[11])
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
        return questionDtoList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<QuestionDto> hasQuestionAnswer(Long questionId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "q.id, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q WHERE q.id =: id ")
                .unwrap(Query.class)
                .setParameter("id", questionId)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .countAnswer(((Number) objects[1]).intValue())
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
        );
    }

    @Override
    public void updateQuestionDtoTitleAndDescription(QuestionDto questionDto) {
        entityManager.createQuery("UPDATE Question " +
                "SET title = :title, description = :description WHERE id = : id")
                .setParameter("title", questionDto.getTitle())
                .setParameter("description", questionDto.getDescription())
                .setParameter("id", questionDto.getId())
                .unwrap(Query.class)
                .executeUpdate();
    }

    @Override
    public void toVoteForQuestion(Long questionId, int vote) {
        entityManager.createQuery("UPDATE Question " +
                "SET countValuable = :vote WHERE id = : id")
                .setParameter("vote", vote)
                .setParameter("id", questionId)
                .unwrap(Query.class)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionList(int page, int size) {
        List<QuestionDto> resultList = entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.title, " +
                "q.user.fullName, " +
                "q.user.reputationCount, " +
                "q.viewCount, " +
                "q.countValuable, " +
                "q.persistDateTime, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT a.user.fullName FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)), " +
                "(SELECT a.persistDateTime FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)) " +
                "FROM Question q ")
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .title((String) objects[1])
                                .userDto(UserDto.builder()
                                        .fullName((String) objects[2])
                                        .reputationCount((Integer) objects[3])
                                        .build())
                                .viewCount((Integer) objects[4])
                                .countValuable((Integer) objects[5])
                                .persistDateTime((LocalDateTime) objects[6])
                                .countAnswer(((Number) objects[7]).intValue())
                                .isHelpful((Boolean) objects[8])
                                .lastAnswerName((String) objects[9])
                                .lastAnswerDate((LocalDateTime) objects[10])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> getTagList(long q_id) {
        List<TagDto> result = entityManager.createQuery(
                "SELECT t.id, t.name, t.description " +
                        "FROM Question q JOIN q.tags t WHERE q.id = :q_id")
                .setParameter("q_id", q_id)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return TagDto.builder()
                                .id((Long) objects[0])
                                .name((String) objects[1])
                                .description((String) objects[2])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();
        return result;
    }

    @Override
    public Long getCount() {
        return (Long) entityManager.createQuery("SELECT COUNT(q) FROM Question q")
                .getSingleResult();
    }
}