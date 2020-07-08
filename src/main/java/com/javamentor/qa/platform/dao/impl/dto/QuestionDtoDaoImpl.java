package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
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
                    "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = q.id), " +
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
                                    .countValuable((objects[7] == null ? 0 : ((Number) objects[7]).intValue()))
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
                "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = :id), " +
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
                "FROM Question q JOIN q.tags t WHERE q.id = :id ")
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
                                .countValuable((objects[7] == null ? 0 : ((Number) objects[7]).intValue()))
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
    public Optional<QuestionDto> getQuestionDtoById(Long questionId, Long userId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.title, " +
                "q.user.id, " +
                "q.user.fullName, " +
                "q.user.reputationCount, " +
                "q.user.imageUser, " +
                "q.viewCount, " +
                "(SELECT SUM (q.vote) FROM VoteQuestion q WHERE q.voteQuestionPK.question.id = :questionId), " +
                "q.persistDateTime, " +
                "q.lastUpdateDateTime, " +
                "q.description, " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id)," +
                "(SELECT a.user.fullName FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)), " +
                "(SELECT a.persistDateTime FROM Answer a WHERE a.question.id = q.id AND a.id = (SELECT MAX(a.id) FROM a WHERE a.question.id = q.id)), " +
                "(SELECT SUM (q.vote) FROM VoteQuestion q WHERE q.voteQuestionPK.question.id = :questionId AND q.voteQuestionPK.user.id = :userId) " +
                "FROM Question q JOIN q.tags t WHERE q.id =: questionId ")
                .unwrap(Query.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
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
                                .countValuable((objects[7] == null ? 0 : ((Number) objects[7]).intValue()))
                                .persistDateTime((LocalDateTime) objects[8])
                                .lastUpdateDateTime((LocalDateTime) objects[9])
                                .description((String) objects[10])
                                .tags(tagDtoList)
                                .countAnswer(((Number) objects[14]).intValue())
                                .isHelpful((Boolean) objects[15])
                                .lastAnswerDate((LocalDateTime) objects[17])
                                .lastAnswerName((String) objects[16])
                                .voteByUser((objects[18] == null ? 0 : ((Number) objects[18]).intValue()))
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
                    "(SELECT SUM (q.vote) FROM VoteQuestion q WHERE q.voteQuestionPK.question.id = :id), " +
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
                                    .countValuable((objects[7] == null ? 0 : ((Number) objects[4]).intValue()))
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

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionList(int page, int size) {
        List<QuestionDto> resultList = entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.title, " +
                "q.user.fullName, " +
                "q.user.reputationCount, " +
                "q.viewCount, " +
                "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = q.id), " +
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
                                .countValuable((objects[5] == null ? 0 : ((Number) objects[5]).intValue()))
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

    @Override
    public Integer getCountValuableWithFalse(Long questionId) {
        Number a = (Long) entityManager.createQuery("SELECT " +
                "COUNT (q) FROM VoteQuestion q WHERE q.vote = '-1' AND q.voteQuestionPK.question.id = :id")
                .unwrap(Query.class)
                .setParameter("id", questionId)
                .getSingleResult();
        return a.intValue();
    }

    @Override
    public Integer getCountValuableWithTrue(Long questionId) {
        Number a = (Long) entityManager.createQuery("SELECT " +
                "COUNT (q) FROM VoteQuestion q WHERE q.vote = '1' AND q.voteQuestionPK.question.id = :id")
                .unwrap(Query.class)
                .setParameter("id", questionId)
                .getSingleResult();
        return a.intValue();
    }

    @Override
    public Integer getCountValuable(Long questionId) {
        Number a = (Long) entityManager.createQuery("SELECT SUM (q.vote) FROM VoteQuestion q " +
                "WHERE q.voteQuestionPK.question.id = :id")
                .unwrap(Query.class)
                .setParameter("id", questionId)
                .getSingleResult();
        return a.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<QuestionDto> getCountValuableQuestionWithUserVote(Long questionId, Long userId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "q.id," +
                "(SELECT SUM (q.vote) FROM VoteQuestion q WHERE q.voteQuestionPK.question.id = :questionId), " +
                "(SELECT SUM (q.vote) FROM VoteQuestion q WHERE q.voteQuestionPK.question.id = :questionId AND q.voteQuestionPK.user.id = :userId) " +
                "FROM Question q WHERE q.id =: questionId ")
                .unwrap(Query.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .countValuable((objects[1] == null ? 0 : ((Number) objects[1]).intValue()))
                                .voteByUser((objects[2] == null ? 0 : ((Number) objects[2]).intValue()))
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
    public Integer sumVotesUserByVote(Long questionId, Long userId) {
        Number a = (Long) entityManager.createQuery("SELECT SUM (q.vote) FROM VoteQuestion q " +
                "WHERE q.voteQuestionPK.question.id = :questionId AND q.voteQuestionPK.user.id = :userId")
                .unwrap(Query.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .getSingleResult();
        return a == null ? 0 : a.intValue();
    }

    @Override
    public Long getQuestionCountByUserId(Long userId) {
        return (Long) entityManager.createQuery("SELECT COUNT(q) " +
                "FROM Question q WHERE q.user.id = :user_id")
                .setParameter("user_id", userId)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> getQuestionDtoByUserIdSortByDate(Long userId, Integer page) {
        List<QuestionDto> questionDtoList = entityManager.createQuery("SELECT " +
                "q.id, " +
                "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = q.id), " +
                "q.viewCount, " +
                "q.persistDateTime, " +
                "q.title, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q " +
                "WHERE q.user.id = :userId " +
                "ORDER BY q.persistDateTime DESC")
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] aliases) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .countValuable((objects[1] == null ? 0 : ((Number) objects[1]).intValue()))
                                .viewCount((Integer) objects[2])
                                .persistDateTime((LocalDateTime) objects[3])
                                .title((String) objects[4])
                                .countAnswer(((Number) objects[5]).intValue())
                                .isHelpful((Boolean) objects[6])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();

        return questionDtoList.isEmpty()? Collections.emptyList() : questionDtoList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> getQuestionDtoByUserIdSortByViews(Long userId, Integer page) {
        List<QuestionDto> questionDtoList = entityManager.createQuery("SELECT " +
                "q.id, " +
                "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = q.id), " +
                "q.viewCount, " +
                "q.persistDateTime, " +
                "q.title, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q " +
                "WHERE q.user.id = :userId " +
                "ORDER BY q.viewCount DESC")
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] aliases) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .countValuable((objects[1] == null ? 0 : ((Number) objects[1]).intValue()))
                                .viewCount((Integer) objects[2])
                                .persistDateTime((LocalDateTime) objects[3])
                                .title((String) objects[4])
                                .countAnswer(((Number) objects[5]).intValue())
                                .isHelpful((Boolean) objects[6])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();

        return questionDtoList.isEmpty()? Collections.emptyList() : questionDtoList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<QuestionDto> getQuestionDtoByUserIdSortByVotes(Long userId, Integer page) {
        List<QuestionDto> questionDtoList = entityManager.createQuery("SELECT " +
                "q.id, " +
                "q.viewCount, " +
                "SUM(v.vote), " +
                "q.persistDateTime, " +
                "q.title, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q LEFT JOIN VoteQuestion v ON q.id = v.voteQuestionPK.question.id " +
                "WHERE q.user.id = :userId " +
                "GROUP BY q.id " +
                "ORDER BY SUM(v.vote) DESC")
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * 20)
                .setMaxResults(20)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] aliases) {
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .viewCount((Integer) objects[1])
                                .countValuable(objects[2]==null ? 0 : ((Number) objects[2]).intValue())
                                .persistDateTime((LocalDateTime) objects[3])
                                .title((String) objects[4])
                                .countAnswer(((Number) objects[5]).intValue())
                                .isHelpful((Boolean) objects[6])
                                .build();
                    }

                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                }).getResultList();

        return questionDtoList.isEmpty()? Collections.emptyList() : questionDtoList;
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecated"})
    public List<QuestionDto> getQuestionsByTagId(Long tagId) {
        List<QuestionDto> questionsDtoByTagId = new ArrayList<>();
        try {
            questionsDtoByTagId = entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.id, " +
                    "q.user.fullName, " +
                    "q.user.reputationCount, " +
                    "q.user.imageUser, " +
                    "q.viewCount, " +
                    "(SELECT SUM (v.vote) FROM VoteQuestion v WHERE v.voteQuestionPK.question.id = q.id) as i, " +
                    "q.persistDateTime,  " +
                    "t.id, " +
                    "t.name, " +
                    "t.description, " +
                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                    "(SELECT CASE WHEN MAX (a.isHelpful) > 0 THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q JOIN q.tags t " +
                    "WHERE t.id = :tI " +
                    "ORDER BY i DESC")
                    .setParameter("tI", tagId)
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
                                    .countValuable((objects[7] == null ? 0 : ((Number) objects[7]).intValue()))
                                    .persistDateTime((LocalDateTime) objects[8])
                                    .tags(tagDtoList)
                                    .countAnswer(((Number) objects[12]).intValue())
                                    .isHelpful((Boolean) objects[13])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            List<QuestionDto> newList = list;
                            newList.forEach(f -> f.setTags(getTagList(f.getId())));
                            return newList;
                        }
                    })
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionsDtoByTagId;
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecated"})
    public List<QuestionDto> getUnansweredQuestions() {
        List<QuestionDto> unansweredQuestions = new ArrayList<>();
        try {
            unansweredQuestions = entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.persistDateTime, " +
                    "q.title, " +
                    "q.description, " +
                    "q.user.fullName, " +
                    "SUM(v.vote), " +
                    "q.user.reputationCount, " +
                    "q.viewCount, " +
                    "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id) as answerCount, " +
                    "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                    "FROM Question q LEFT JOIN VoteQuestion v ON q.id = v.voteQuestionPK.question.id " +
                    "GROUP BY q.id " +
                    "ORDER BY answerCount, SUM (v.vote) DESC, q.viewCount DESC")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] tuple, String[] aliases) {
                            UserDto userDto = UserDto.builder()
                                    .fullName((String) tuple[4])
                                    .reputationCount(((Number) tuple[6]).intValue())
                                    .build();
                            List<TagDto> tagDtoList = new ArrayList<>();
                            return QuestionDto.builder()
                                    .id(((Number) tuple[0]).longValue())
                                    .persistDateTime((LocalDateTime) tuple[1])
                                    .title((String) tuple[2])
                                    .description((String) tuple[3])
                                    .userDto(userDto)
                                    .countValuable((tuple[5] == null ? 0 : ((Number) tuple[5]).intValue()))
                                    .countAnswer(((Number) tuple[8]).intValue())
                                    .isHelpful((Boolean) tuple[9])
                                    .viewCount(((Number) tuple[7]).intValue())
                                    .tags(tagDtoList)
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            List<QuestionDto> newList = list;
                            newList.forEach(f -> f.setTags(getTagList(f.getId())));
                            return list;
                        }
                    })
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return unansweredQuestions;
    }
}