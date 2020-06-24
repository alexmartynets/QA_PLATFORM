package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.AnswerDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.model.ReputationDAO;
import com.javamentor.qa.platform.dao.abstracts.model.UserFavoriteQuestionDAO;
import com.javamentor.qa.platform.models.dto.*;
import com.javamentor.qa.platform.service.abstracts.dto.UserStatisticDtoService;
import com.javamentor.qa.platform.service.statistics.main.GetUserStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStatisticDtoServiceImpl implements UserStatisticDtoService {

    private UserDto user;

    private final AnswerDtoDAO answerDtoDAO;
    private final QuestionDtoDao questionDtoDao;
    private final UserDtoDAO userDtoDAO;
    private final ReputationDAO reputationDAO;
    private final GetUserStatistics getUserStatistics;

    @Autowired
    public UserStatisticDtoServiceImpl(AnswerDtoDAO answerDtoDAO,
                                       QuestionDtoDao questionDtoDao,
                                       UserDtoDAO userDtoDAO,
                                       ReputationDAO reputationDAO,
                                       GetUserStatistics getUserStatistics ) {
        this.answerDtoDAO = answerDtoDAO;
        this.questionDtoDao = questionDtoDao;
        this.userDtoDAO = userDtoDAO;
        this.reputationDAO = reputationDAO;
        this.getUserStatistics = getUserStatistics;
    }

    @Override
    public UserStatisticDto getUserStatistic(UserDto user, String typeTabsAndSort) {
        this.user = user;
        typeTabsAndSort +=":"+user.getId();
        UserStatisticDto userStatisticDto = getUserStatistics.getResult(typeTabsAndSort);
        userStatisticDto.setTotalUserAnswers(reputationDAO.getSummOfUserReputation(user.getId()));
        userStatisticDto.setTotalUserQuestions(questionDtoDao.getQuestionCountByUserId(user.getId()));
        userStatisticDto.setTotalUserReputation(reputationDAO.getSummOfUserReputation(user.getId()));
        userStatisticDto.setAllViews(userDtoDAO.getAllViews(user.getId()));
        userStatisticDto.setUserDto(user);
        return userStatisticDto;



//        return tabDecorator.getStatistics(user.getId(), "a.viewCount", 1);
//        switch (tab) {
//            case "answers":
//                userStatisticDto.setAnswerList(answerDtoDAO.getAnswerDtoByUserId(user.getId(), answerSort, page));
//                return userStatisticDto;
//
//            case "questions":
//                setQuestionDtoFromDB();
//                return userStatisticDto;
//
//            case "tags":
//                setTagDtoFromDB();
//                return userStatisticDto;
//
//            case "badges":
//                userStatisticDto.setUserBadges(userDtoDAO.getUserBadges(user.getId()));
//                return userStatisticDto;
//
//            case "reputation":
//                userStatisticDto.setUserReputation(reputationDAO.getReputationByUserId(user.getId()));
//                return userStatisticDto;
//
//            case "bookmarks":
//                setBookmarks();
//                return userStatisticDto;
//
//            default:
//                setSorting("votes");
//                userStatisticDto.setUserBadges(userDtoDAO.getUserBadges(user.getId()));
//                userStatisticDto.setUserReputation(reputationDAO.getReputationByUserId(user.getId()));
//                userStatisticDto.setAnswerList(answerDtoDAO.getAnswerDtoByUserId(user.getId(), answerSort, page));
//                setQuestionDtoFromDB();
//                setTagDtoFromDB();
//                setBookmarks();
//                return userStatisticDto;
//
//        }
    }

//    private void setQuestionDtoFromDB() {
//        List<QuestionDto> questionDtolist = questionDtoDao.getSortingQuestionDtoByUserId(user.getId(), questionSort, page);
//        questionDtolist.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
//        userStatisticDto.setQuestionDtoList(questionDtolist);
//    }
//
//    private void setTagDtoFromDB() {
//        List<TagDto> tagDtoListQ = questionDtoDao.getTagsByUserId(user.getId());
//        List<TagDto> tagDtoListA = answerDtoDAO.getTagsFromAnswerByUserId(user.getId());
////        userStatisticDto.setTagDtoList(deleteDuplicateTags(tagDtoList));
//    }
//
//    private void setBookmarks(){
//        List<QuestionDto> questionDtoList1 = userFavoriteQuestionDAO.getUserFavorite(user.getId(), questionSort, page);
//        questionDtoList1.forEach(f -> f.setTags(questionDtoDao.getTagList(f.getId())));
//        userStatisticDto.setUserFavoriteQuestions(questionDtoList1);
//    }
//
//    private Map<TagDto, Integer> deleteDuplicateTags(List<TagDto> tagDtoList) {
//        Map<TagDto, Integer> map = new HashMap<>();
//        Long tid = 0l;
//        Long qid = 0l;
//        for (TagDto t : tagDtoList) {
//            if (t.getId() != tid || t.getId() != qid) {
//                TagDto tagDto = TagDto.builder().id(t.getId())
//                        .name(t.getName())
//                        .description(t.getDescription())
//                        .build();
//                if (map.containsKey(tagDto)) {
//                    map.put(tagDto, map.get(tagDto) + 1);
//                } else {
//                    map.put(tagDto, 1);
//                }
//            }
//            tid = t.getId();
//            qid = t.getId();
//        }
//        return map;
//    }
//
//    private void setSorting(String sort) {
//        switch (sort) {
//            case "newest":
//                questionSort = "q.persistDateTime";
//                answerSort = "a.persistDateTime";
//                break;
//            case "views":
//                questionSort = "q.viewCount";
//                answerSort = "a.viewCount";
//                break;
//            case "votes":
//                questionSort = "q.countValuable";
//                answerSort = "a.countValuable";
//                break;
//        }
//    }
}
