package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.SearchQuestionDAO;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.hibernate.query.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class SearchQuestionDaoImpl extends ReadWriteDAOImpl<QuestionDto, Long> implements SearchQuestionDAO {

    private static final String QUERY = "SELECT " +
            "q.id, " +
            "q.persistDateTime, " +
            "q.title, " +
            "q.description, " +
            "q.user.fullName, " +
            "(SELECT SUM (v.vote) FROM Question qw RIGHT OUTER JOIN VoteQuestion v WHERE VoteQuestion.question.id = q.id), " +
            "q.user.reputationCount, " +
            "q.viewCount, " +
            "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
            "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id), " +
            "t.id, " +
            "t.name, " +
            "t.description";

    @SuppressWarnings("unchecked")
    @Override
    public Optional<QuestionDto> settersForCommonSearch(Long questionId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT " +
                "q.id, " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id), " +
                "(SELECT CASE WHEN MAX (a.isHelpful) > false THEN true ELSE false END FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q " +
                "JOIN q.tags t " +
                "WHERE q.id = :questionId")
                .setParameter("questionId", questionId)
                .unwrap(Query.class)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        TagDto tagDto = TagDto.builder()
                                .id((Long) tuple[1])
                                .name((String) tuple[2])
                                .description((String) tuple[3])
                                .build();
                        List<TagDto> tagDtoList = new ArrayList<>();
                        tagDtoList.add(tagDto);
                        return QuestionDto.builder()
                                .id(((Number) tuple[0]).longValue())
                                .tags(tagDtoList)
                                .isHelpful((Boolean) tuple[5])
                                .countAnswer(((Number) tuple[4]).intValue())
                                .build();
                    }

                    @Override
                    public List transformList(List collection) {
                        Map<Long, QuestionDto> result = new TreeMap<>(Comparator.reverseOrder());
                        for (Object obj : collection) {
                            QuestionDto questionDto = (QuestionDto) obj;
                            if (result.containsKey(questionDto.getId())) {
                                result.get(questionDto.getId()).getTags().addAll(questionDto.getTags());
                            } else result.put(questionDto.getId(), questionDto);
                        }
                        return new ArrayList<>(result.values());
                    }
                }));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByUserId(Long id) {
        List<QuestionDto> questionDtoListByUserId = new ArrayList<>();
        try {
            questionDtoListByUserId = entityManager.createQuery(QUERY +
                    " FROM Question q JOIN q.tags t WHERE q.user.id = :id")
                    .setParameter("id", id)
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListByUserId;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsSortedByVotes() {
        List<QuestionDto> questionDtoListSortedByVotes = new ArrayList<>();
        try {
            questionDtoListSortedByVotes = entityManager.createQuery(QUERY +
                    //todo исправить
                    " FROM Question q JOIN q.tags t INNER JOIN VoteQuestion v ON q.id = v.voteQuestionPK.question.id ORDER BY SUM (v.vote) DESC")
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListSortedByVotes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByNumberOfAnswers(Long numberOfAnswers) {
        List<QuestionDto> questionDtoListByNumberOfAnswers = new ArrayList<>();
        try {
            questionDtoListByNumberOfAnswers = entityManager.createQuery(QUERY +
                    " FROM Question q JOIN q.tags t WHERE (SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id) >= :numberOfAnswers")
                    .setParameter("numberOfAnswers", numberOfAnswers)
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListByNumberOfAnswers;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByNumberOfVotes(Long numberOfVotes) {
        List<QuestionDto> questionDtoListByNumberOfVotes = new ArrayList<>();
        try {//todo исправить
            questionDtoListByNumberOfVotes = entityManager.createQuery(QUERY +
                    " FROM Question q JOIN q.tags t WHERE q.countValuable >= :numberOfVotes ORDER BY q.countValuable ASC")
                    .setParameter("numberOfVotes", numberOfVotes)
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListByNumberOfVotes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByFieldHelpfulFalse() {
        List<QuestionDto> questionDtoListByFieldHelpful = new ArrayList<>();
        try {
            questionDtoListByFieldHelpful = entityManager.createQuery(QUERY +
                    " FROM Question q LEFT JOIN Answer a ON q.id = a.question.id " +
                    "JOIN q.tags t WHERE false = ALL (SELECT a.isHelpful FROM Answer a WHERE q.id = a.question.id)")
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListByFieldHelpful;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByFieldHelpfulTrue() {
        List<QuestionDto> questionDtoListByFieldHelpful = new ArrayList<>();
        try {
            questionDtoListByFieldHelpful = entityManager.createQuery(QUERY +
                    " FROM Question q LEFT JOIN Answer a ON q.id = a.question.id " +
                    "JOIN q.tags t WHERE a.isHelpful = true ")
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return questionDtoListByFieldHelpful;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean getTagByName(String name) {
        try {
            entityManager
                    .createQuery("SELECT t " +
                            "FROM Tag t " +
                            "WHERE t.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionsByTag(String tag) {
        List<QuestionDto> questionDtoListByTag = new ArrayList<>();
        try {
            questionDtoListByTag = entityManager.createQuery(QUERY +
                    " FROM Question q JOIN q.tags t WHERE t.name = :tag")
                    .setParameter("tag", tag)
                    .unwrap(Query.class)
                    .setResultTransformer(result())
                    .getResultList();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return questionDtoListByTag;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> search(String text) {

        FullTextEntityManager fullTextEntityManager = Search
                .getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Question.class)
                .get();

        FullTextQuery query = fullTextEntityManager.createFullTextQuery(queryBuilder
                .simpleQueryString()
                .onField("description")
                .matching(text)
                .createQuery())
                .setProjection("id", "description", "title", "countValuable", "persistDateTime", "user.fullName", "user.reputationCount", "viewCount")
                .setResultTransformer(new ResultTransformer() {

                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        return QuestionDto.builder()
                                .id(((Number) tuple[0]).longValue())
                                .title((String) tuple[2])
                                .description((String) tuple[1])
                                .countValuable(((Number) tuple[3]).intValue())
                                .persistDateTime((LocalDateTime) tuple[4])
                                .userDto(UserDto.builder()
                                        .fullName((String) tuple[5])
                                        .reputationCount(((Number) tuple[6]).intValue())
                                        .build())
                                .viewCount(((Number) tuple[7]).intValue())
                                .build();
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                });
        return query.getResultList();
    }

    private ResultTransformer result() {
        return new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                UserDto userDto = UserDto.builder()
                        .fullName((String) tuple[4])
                        .reputationCount(((Number)tuple[6]).intValue())
                        .build();
                TagDto tagDto = TagDto.builder()
                        .id((Long) tuple[10])
                        .name((String) tuple[11])
                        .description((String) tuple[12])
                        .build();
                List<TagDto> tagDtoList = new ArrayList<>();
                tagDtoList.add(tagDto);
                return QuestionDto.builder()
                        .id(((Number) tuple[0]).longValue())
                        .persistDateTime((LocalDateTime) tuple[1])
                        .title((String) tuple[2])
                        .description((String) tuple[3])
                        .userDto(userDto)
                        .countValuable(((Number) tuple[5]).intValue())
                        .countAnswer(((Number) tuple[8]).intValue())
                        .isHelpful((Boolean) tuple[9])
                        .viewCount(((Number)tuple[7]).intValue())
                        .tags(tagDtoList)
                        .build();
            }

            @Override
            public List transformList(List collection) {
                Map<Long, QuestionDto> result = new TreeMap<>(Comparator.reverseOrder());
                for (Object obj : collection) {
                    QuestionDto questionDto = (QuestionDto) obj;
                    if (result.containsKey(questionDto.getId())) {
                        result.get(questionDto.getId()).getTags().addAll(questionDto.getTags());
                    } else result.put(questionDto.getId(), questionDto);
                }
                return new ArrayList<>(result.values());
            }
        };
    }
}