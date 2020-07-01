package com.javamentor.qa.platform.recourse.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRecourseControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = {"searchDatasets/roleSearch.yml", "searchDatasets/usersSearch.yml"}, cleanBefore = true)
    void getAllDto() {
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserDtoAndAnswerPairFromUserStatisticVotesSorting() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=answer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.answerList.key").value("2"))
                .andExpect(jsonPath("$.answerList.value[0].id").value("5"))
                .andExpect(jsonPath("$.answerList.value[-1].id").value("2"))
                .andExpect(jsonPath("$.answerList.value.size()").value("2"));

    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserDtoAndAnswerPairFromUserStatisticDataSorting() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=answer&sort=newest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.answerList.key").value("2"))
                .andExpect(jsonPath("$.answerList.value[0].id").value("2"))
                .andExpect(jsonPath("$.answerList.value[-1].id").value("5"))
                .andExpect(jsonPath("$.answerList.value.size()").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserDtoAndAnswerPairFromUserStatisticSortingByViews() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=answer&sort=views"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.answerList.key").value("2"))
                .andExpect(jsonPath("$.answerList.value[0].id").value("2"))
                .andExpect(jsonPath("$.answerList.value[-1].id").value("5"))
                .andExpect(jsonPath("$.answerList.value.size()").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testQuestionPairFromUserStatisticSortingByVotes() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=question"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.questionDtoList.key").value("3"))
                .andExpect(jsonPath("$.questionDtoList.value[0].id").value("1"))
                .andExpect(jsonPath("$.questionDtoList.value[-1].id").value("5"))
                .andExpect(jsonPath("$.questionDtoList.value.size()").value("3"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testQuestionPairFromUserStatisticSortingByView() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=question&sort=views"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.questionDtoList.key").value("3"))
                .andExpect(jsonPath("$.questionDtoList.value[0].id").value("2"))
                .andExpect(jsonPath("$.questionDtoList.value[-1].id").value("5"))
                .andExpect(jsonPath("$.questionDtoList.value.size()").value("3"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testQuestionPairFromUserStatisticSortingByDate() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=question&sort=newest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.questionDtoList.key").value("3"))
                .andExpect(jsonPath("$.questionDtoList.value[0].id").value("5"))
                .andExpect(jsonPath("$.questionDtoList.value[-1].id").value("1"))
                .andExpect(jsonPath("$.questionDtoList.value.size()").value("3"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml",
            "userStatistics/userFavoriteQuestionStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserFavoriteQuestionPairFromUserStatisticSortingByVotes() throws Exception { //??
        this.mockMvc.perform(get("/api/user/1/Teat?tab=bookmarks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.userFavoriteQuestions.key").value("2"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[0].id").value("3"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[-1].id").value("8"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value.size()").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml",
            "userStatistics/userFavoriteQuestionStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserFavoriteQuestionPairFromUserStatisticSortingByDate() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=bookmarks&sort=newest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.userFavoriteQuestions.key").value("2"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[0].id").value("8"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[-1].id").value("3"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value.size()").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml",
            "userStatistics/userFavoriteQuestionStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserFavoriteQuestionPairFromUserStatisticSortingByViews() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=bookmarks&sort=views"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.userFavoriteQuestions.key").value("2"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[0].id").value("3"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value[-1].id").value("8"))
                .andExpect(jsonPath("$.userFavoriteQuestions.value.size()").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testTagPairFromUserStatistic() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=tags"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.tagDtoList.key").value("2"))
                .andExpect(jsonPath("$.tagDtoList.value[0].tagId").value("1"))
                .andExpect(jsonPath("$.tagDtoList.value[-1].tagId").value("2"))
                .andExpect(jsonPath("$.tagDtoList.value[0].countOfTag").value("3"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/badgesStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/user_badgesStatistics.yml"
    }, cleanBefore = true, cleanAfter = true)
    void testUserBadgesPairFromUserStatistic() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=badges"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.userBadges.key").value("2"))
                .andExpect(jsonPath("$.userBadges.value[0].id").value("1"))
                .andExpect(jsonPath("$.userBadges.value[0].badges").value("Helper"))
                .andExpect(jsonPath("$.userBadges.value[-1].id").value("2"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/badgesStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/user_badgesStatistics.yml"
    }, cleanBefore = true, cleanAfter = true)
    void testUserReputationFromUserStatistic() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=reputation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.userReputation.size()").value("2"))
                .andExpect(jsonPath("$.userReputation[0].count").value("30"))
                .andExpect(jsonPath("$.userReputation[1].count").value("100"));
    }

    @Test
    @DataSet(value = {"userStatistics/usersStatistics.yml",
            "userStatistics/roleStatistics.yml",
            "userStatistics/reputationStatistics.yml",
            "userStatistics/questionStatistics.yml",
            "userStatistics/answerStatistics.yml",
            "userStatistics/tagsStatistics.yml",
            "userStatistics/badgesStatistics.yml",
            "userStatistics/q_has_tagStatistics.yml",
            "userStatistics/userFavoriteQuestionStatistics.yml",
            "userStatistics/user_badgesStatistics.yml"}, cleanBefore = true, cleanAfter = true)
    void testUserStatisticsProfileTabFromUserStatisticSortingByViews() throws Exception {
        this.mockMvc.perform(get("/api/user/1/Teat?tab=profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUserReputation").value("130"))
                .andExpect(jsonPath("$.totalUserQuestions").value("3"))
                .andExpect(jsonPath("$.totalUserAnswers").value("2"))
                .andExpect(jsonPath("$.allViews").value("12"))
                .andExpect(jsonPath("$.userDto.id").value("1"))
                .andExpect(jsonPath("$.userDto.fullName").value("Teat"))
                .andExpect(jsonPath("$.userDto.persistDateTime").value("2020-05-28T13:58:56"))
                .andExpect(jsonPath("$.questionDtoList.key").value("3"))
                .andExpect(jsonPath("$.answerList.key").value("2"))
                .andExpect(jsonPath("$.tagDtoList.key").value("2"))
                .andExpect(jsonPath("$.userBadges.key").value("2"))
                .andExpect(jsonPath("$.userFavoriteQuestions.key").value("2"))
                .andExpect(jsonPath("$.userReputation.size()").value("2"));
    }

}
