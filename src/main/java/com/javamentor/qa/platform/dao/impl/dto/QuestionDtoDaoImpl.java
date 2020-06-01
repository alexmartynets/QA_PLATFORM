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
                "q.user.email, " +
                "q.user.role.name, " +
                "q.user.persistDateTime, " +
                "q.user.reputationCount, " +
                "q.user.about, " +
                "q.user.city, " +
                "q.user.imageUser, " +
                "q.user.lastUpdateDateTime, " +
                "q.user.linkGitHub, " +
                "q.user.linkSite, " +
                "q.user.linkVk, " +
                "q.viewCount, " +
                "q.countValuable, " +
                "q.persistDateTime, " +
                "q.description, " +
                "q.isDeleted, " +
                "t.id, " +
                "t.name, " +
                "t.description, " +
                "(SELECT COUNT (a) FROM Answer a WHERE a.question.id = q.id) " +
                "FROM Question q JOIN q.tags t WHERE q.id =: id ")
                .unwrap(Query.class)
                .setParameter("id", id)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        UserDto userDto = UserDto.builder()
                                .id(((Number) objects[2]).longValue())
                                .fullName((String) objects[3])
                                .email((String) objects[4])
                                .role((String) objects[5])
                                .persistDateTime((LocalDateTime) objects[6])
                                .reputationCount((Integer) objects[7])
                                .about((String) objects[8])
                                .city((String) objects[9])
                                .imageUser((byte[]) objects[10])
                                .lastUpdateDateTime((LocalDateTime) objects[11])
                                .linkGitHub((String) objects[12])
                                .linkSite((String) objects[13])
                                .linkVk((String) objects[14])
                                .build();
                        TagDto tagDto = TagDto.builder()
                                .id((Long) objects[20])
                                .name((String) objects[21])
                                .description((String) objects[22])
                                .build();
                        List<TagDto> tagDtoList = new ArrayList<>();
                        tagDtoList.add(tagDto);
                        return QuestionDto.builder()
                                .id((Long) objects[0])
                                .title((String) objects[1])
                                .userDto(userDto)
                                .viewCount((Integer) objects[15])
                                .countValuable((Integer) objects[16])
                                .persistDateTime((LocalDateTime) objects[17])
                                .description((String) objects[18])
                                .tags(tagDtoList)
                                .isDeleted((Boolean) objects[19])
                                .countAnswer(((Number) objects[23]).intValue())
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
    public List<QuestionDto> getQuestionDtoListByUserId(Long userId) {//todo настроить
        List<QuestionDto> questionDtoList = new ArrayList<>();
        try {
            questionDtoList = entityManager.createQuery("SELECT " +
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
                    "FROM Question q JOIN q.tags t WHERE q.id =: id ")
                    .unwrap(Query.class)
                    .setParameter("id", userId)
                    .setResultTransformer(new ResultTransformer() {
                        @Override
                        public Object transformTuple(Object[] objects, String[] strings) {
                            UserDto userDto = UserDto.builder()
                                    .id((Long) objects[2])
                                    .build();
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
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionDtoList;
    }
}