package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracrt.dto.QuestionDaoDto;
import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.ToListResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class QuestionDaoDtoImpl extends AbstractDAOImpl<QuestionDto, Long> implements QuestionDaoDto {

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getListQuestionDto() {
        List<QuestionDto> questionDto = new ArrayList<>();
        try {
            questionDto = entityManager.createQuery("SELECT " +
                    "q.id AS id, " +
                    "q.title AS title, " +
                    "q.user.fullName AS username, " +
                    "q.user.reputationCount AS reputationCount, " +
                    "q.viewCount AS viewCount, " +
                    "q.countValuable AS countValuable, " +
                    "q.persistDateTime AS persistDateTime " +
                    "FROM Question q")
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                                              @Override
                                              public Object transformTuple(Object[] objects, String[] strings) {
                                                  return QuestionDto.builder()
                                                          .id((Long) objects[0])
                                                          .title((String) objects[1])
                                                          .username((String) objects[2])
                                                          .reputationCount((Integer) objects[3])
                                                          .viewCount((Integer) objects[4])
                                                          .countValuable((Integer) objects[5])
                                                          .persistDateTime((LocalDateTime) objects[6])
                                                          .build();
                                              }

                                              @Override
                                              public List transformList(List list) {
                                                  return list;
                                              }
                                          })
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return questionDto;
    }

//    private List<TagDto> getTagsByQuestionId(@NotNull Long questionId) {
//        List<TagDto> tags = new ArrayList<>();
//        try {
//           tags = entityManager.createNativeQuery("SELECT t.name FROM tag t LEFT JOIN question_has_tag qht on t.id = qht.tag_id WHERE qht.question_id = :questionId")
//                   .setParameter("questionId", questionId).getResultList();
//       } catch (Exception e){
//           e.printStackTrace();
//       }
//        return tags;
//    }
//
//    public List<QuestionDto> getListQuestionDtoByListQuestionId(List<Long> listQuestionId) {
//        return listQuestionId
//                .stream()
//                .map(this::getQuestionDtoByQuestionId)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }
//
//    public List<Long> getListQuestionId() {
//        List<Long> questionIdList = new ArrayList<>();
//        try {
//            questionIdList = entityManager.createNativeQuery("SELECT question.id FROM question").getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return questionIdList;
//    }
}