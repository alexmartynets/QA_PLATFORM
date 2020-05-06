package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracrt.dto.QuestionDaoDto;
import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class QuestionDaoDtoImpl extends AbstractDAOImpl<QuestionDto, Long> implements QuestionDaoDto {


    @Override
    public Optional<QuestionDto> getQuestionDtoByQuestionId(@NotNull Long questionId) {
        QuestionDto questionDto = null;
        try {
            questionDto = (QuestionDto) entityManager.createNativeQuery("SELECT " +
                    "q.id AS \"id\", " +
                    "q.title AS \"title\", " +
                    "u.full_name AS \"username\", " +
                    "u.reputation_count AS \"reputationCount\", " +
                    "q.view_count AS \"viewCount\", " +
                    "q.count_valuable AS \"countValuable\", q.persist_date AS \"persistDateTime\" " +
                    "FROM question q, users u WHERE q.id = :questionId")

                    .setParameter("questionId", questionId)
                    .unwrap(NativeQuery.class)
                    .setResultTransformer(Transformers.aliasToBean(QuestionDto.class))
                    .getSingleResult();
                questionDto.setTagName(getTagsByQuestionId(questionId));
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.ofNullable(questionDto);
    }

    private List<String> getTagsByQuestionId(@NotNull Long questionId) {
        List<String> tags = new ArrayList<>();
        try {
           tags = entityManager.createNativeQuery("SELECT t.name FROM tag t LEFT JOIN question_has_tag qht on t.id = qht.tag_id WHERE qht.question_id = :questionId")
                   .setParameter("questionId", questionId).getResultList();
       } catch (Exception e){
           e.printStackTrace();
       }
        return tags;
    }

    public List<QuestionDto> getListQuestionDtoByListQuestionId(List<Long> listQuestionId) {
        return listQuestionId
                .stream()
                .map(this::getQuestionDtoByQuestionId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> getListQuestionId() {
        List<Long> questionIdList = new ArrayList<>();
        try {
            questionIdList = entityManager.createNativeQuery("SELECT question.id FROM question").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionIdList;
    }
}