package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                            List<QuestionDto> questionDtoList = (List<QuestionDto>) list;
                            Set<QuestionDto> resultSet = new HashSet<>();
                            for (QuestionDto question : questionDtoList) {
                                Set<TagDto> setTag = new HashSet<>();
                                for (QuestionDto question1 : questionDtoList) {
                                    if (question.getId().equals(question1.getId())) {
                                        setTag.addAll(question.getTags());
                                        setTag.addAll(question1.getTags());
                                    }
                                }
                                List<TagDto> tagDtoList = new ArrayList<>(setTag);
                                question.setTags(tagDtoList);
                                resultSet.add(question);
                            }
                            return new ArrayList<>(resultSet);
                        }
                    })
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionDto;
    }
}