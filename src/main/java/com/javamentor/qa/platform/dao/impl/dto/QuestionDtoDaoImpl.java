package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDAOImpl;
import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class QuestionDtoDaoImpl extends ReadWriteDAOImpl<QuestionDto, Long> implements QuestionDtoDao {

    @Autowired
    private UserDtoDao userDtoDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionDto> getQuestionDtoList() {
        List<QuestionDto> questionDto = new ArrayList<>();
        try {
            questionDto = entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.id, " +
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
                            UserDto userDto = userDtoDao.getUserDtoById((Long) objects[2]).get();
                            TagDto tagDto = TagDto.builder()
                                    .id((Long) objects[6])
                                    .name((String) objects[7])
                                    .description((String) objects[8])
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
                                    .tags(tagDtoList)
                                    .countAnswer(((Number) objects[9]).intValue())
                                    .isHelpful((Boolean) objects[10])
                                    .build();
                        }

                        @Override
                        public List transformList(List list) {
                            Map<Long, QuestionDto> result = new TreeMap<>(Comparator.reverseOrder());
                            for (Object obj : list) {
                                QuestionDto questionDto = (QuestionDto) obj;
                                if (result.containsKey(questionDto.getId()))
                                    result.get(questionDto.getId()).getTags().addAll(questionDto.getTags());
                                else result.put(questionDto.getId(), questionDto);
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

    @Override
    public QuestionDto getQuestionDtoById(Long id) {
        QuestionDto questionDto = null;
        try {
            questionDto = (QuestionDto) entityManager.createQuery("SELECT " +
                    "q.id, " +
                    "q.title, " +
                    "q.user.id, " +
                    "q.viewCount, " +
                    "q.countValuable, " +
                    "q.persistDateTime, " +
                    "q.description," +
                    "t.id, " +
                    "t.name, " +
                    "t.description " +
                    "FROM Question q JOIN q.tags t WHERE q.id = " + id)
                    .unwrap(Query.class)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            UserDto userDto = userDtoDao.getUserDtoById((Long) objects[2]).get();
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
                                    .userDto(userDto)
                                    .viewCount((Integer) objects[3])
                                    .countValuable((Integer) objects[4])
                                    .persistDateTime((LocalDateTime) objects[5])
                                    .description((String) objects[6])
                                    .tags(tagDtoList)
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