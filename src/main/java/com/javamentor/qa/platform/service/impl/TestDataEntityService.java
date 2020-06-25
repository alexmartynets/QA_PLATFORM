package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.Badges;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.*;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.AnswerVote;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.*;
import com.javamentor.qa.platform.service.abstracts.model.*;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentAnswerService;
import com.javamentor.qa.platform.service.abstracts.model.comment.CommentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestDataEntityService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentAnswerService commentAnswerService;

    @Autowired
    private CommentQuestionService commentQuestionService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserFavoriteQuestionService userFavoriteQuestionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private RelatedTagService relatedTagService;

    private final ReputationService reputationService;
    private final BadgesService badgesService;
    private final UserBadgesService userBadgesService;
    private final VoteQuestionService voteQuestionService;
    private final AnswerVoteService answerVoteService;


    @Autowired
    public TestDataEntityService(ReputationService reputationService,
                                 BadgesService badgesService,
                                 UserBadgesService userBadgesService,
                                 VoteQuestionService voteQuestionService, AnswerVoteService answerVoteService) {
        this.reputationService = reputationService;
        this.badgesService = badgesService;
        this.userBadgesService = userBadgesService;
        this.voteQuestionService = voteQuestionService;
        this.answerVoteService = answerVoteService;
    }

    public void createEntity() {
        creatUserEntity();
        creatTagEntity();
        creatQuestionEntity();
        creatAnswerEntity();
        creatComment();
        creatUserFavoriteQuestion();
        createBadges();
        createUserBadges();
    }

    private void creatUserEntity() {
        Integer reputationCount = 0;

        Role adminRole = Role.builder()
                .name("ADMIN")
                .build();

        Role userRole = Role.builder()
                .name("USER")
                .build();

        User admin = User.builder()
                .email("admin@admin.ru")
                .password("admin")
                .fullName("Админ Админович Админов")
                .reputationCount(reputationCount)
                .city("Moscow")
                .linkSite("site.admin.ru")
                .linkGitHub("github.admin.ru")
                .linkVk("vk.admin.ru")
                .about("about admin")
                .role(adminRole)
                .isEnabled(true)
                .build();
        userService.persist(admin);

        User user1 = User.builder()
                .email("user1@user.ru")
                .password("user1")
                .fullName("Иван Иванович Иванов")
                .reputationCount(reputationCount)
                .city("Moscow")
                .linkSite("site.user1.ru")
                .linkGitHub("github.user1.ru")
                .linkVk("vk.user1.ru")
                .about("about user1")
                .role(userRole)
                .isEnabled(true)
                .build();
        userService.persist(user1);

        User user2 = User.builder()
                .email("user2@user.ru")
                .isEnabled(true)
                .password("user2")
                .fullName("Петр2 Петрович2 Петров2")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(userRole)
                .build();
        userService.persist(user2);

        User user3 = User.builder()
                .email("user3@user.ru")
                .isEnabled(true)
                .password("user3")
                .fullName("Петр3 Петрович3 Петров3")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user3.ru")
                .linkGitHub("github.user3.ru")
                .linkVk("vk.user3.ru")
                .about("about user3")
                .role(userRole)
                .build();
        userService.persist(user3);

        User user4 = User.builder()
                .email("user4@user.ru")
                .isEnabled(true)
                .password("user4")
                .fullName("Петр4 Петрович4 Петров4")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(userRole)
                .build();
        userService.persist(user4);

        User user5 = User.builder()
                .email("user5@user.ru")
                .isEnabled(true)
                .password("user5")
                .fullName("Петр5 Петрович5 Петров5")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user5.ru")
                .linkGitHub("github.user5.ru")
                .linkVk("vk.user5.ru")
                .about("about user5")
                .role(userRole)
                .build();
        userService.persist(user5);

        User user6 = User.builder()
                .email("user6@user.ru")
                .isEnabled(true)
                .password("user6")
                .fullName("Петр6 Петрович6 Петров6")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user6.ru")
                .linkGitHub("github.user6.ru")
                .linkVk("vk.user6.ru")
                .about("about user6")
                .role(userRole)
                .build();
        userService.persist(user6);

        User user7 = User.builder()
                .email("user7@user.ru")
                .isEnabled(true)
                .password("user7")
                .fullName("Петр7 Петрович7 Петров7")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user7.ru")
                .linkGitHub("github.user7.ru")
                .linkVk("vk.user7.ru")
                .about("about user7")
                .role(userRole)
                .build();
        userService.persist(user7);

        User user8 = User.builder()
                .email("user8@user.ru")
                .isEnabled(true)
                .password("user8")
                .fullName("Петр8 Петрович8 Петров8")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user8.ru")
                .linkGitHub("github.user8.ru")
                .linkVk("vk.user8.ru")
                .about("about user8")
                .role(userRole)
                .build();
        userService.persist(user8);

        User user9 = User.builder()
                .email("user9@user.ru")
                .isEnabled(true)
                .password("user9")
                .fullName("Петр9 Петрович9 Петров9")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user9.ru")
                .linkGitHub("github.user9.ru")
                .linkVk("vk.user9.ru")
                .about("about user9")
                .role(userRole)
                .build();
        userService.persist(user9);

        User user10 = User.builder()
                .email("user10@user.ru")
                .isEnabled(true)
                .password("user10")
                .fullName("Петр10 Петрович10 Петров10")
                .reputationCount(reputationCount)
                .city("SPB")
                .linkSite("site.user10.ru")
                .linkGitHub("github.user10.ru")
                .linkVk("vk.user10.ru")
                .about("about user10")
                .role(userRole)
                .build();
        userService.persist(user10);

    }

    private void creatTagEntity() {
        Tag tag1 = Tag.builder()
                .name("Main tag1")
                .description("Description tag1")
                .build();
        tagService.persist(tag1);

        Tag tag2 = Tag.builder()
                .name("Child tag1")
                .description("Description tag2")
                .build();
        tagService.persist(tag2);

        Tag tag3 = Tag.builder()
                .name("Main tag3")
                .description("Description tag3")
                .build();
        tagService.persist(tag3);

        RelatedTag relatedTag = RelatedTag.builder()
                .mainTag(tag1)
                .childTag(tag2)
                .build();
        relatedTagService.persist(relatedTag);

    }

    private void creatQuestionEntity() {

        List<Question> questionList1 = new ArrayList<>();
        List<Question> questionList2 = new ArrayList<>();
        List<Question> questionList3 = new ArrayList<>();

        Question question1 = Question.builder()
                .title("Question1 title")
                .viewCount(3)
                .description("Question1 description")
                .user(userService.getByKey(2L))
                .isDeleted(false)
                .build();
        List<Tag> tagList1 = new ArrayList<>();
        tagList1.add(tagService.getByKey(1L));
        tagList1.add(tagService.getByKey(2L));
        question1.setTags(tagList1);
        questionList1.add(question1);
        questionList2.add(question1);

        Question question2 = Question.builder()
                .title("Question2 title")
                .viewCount(4)
                .description("Question2 description")
                .user(userService.getByKey(3L))
                .isDeleted(false)
                .build();
        List<Tag> tagList2 = new ArrayList<>();
        tagList2.add(tagService.getByKey(2L));
        question2.setTags(tagList2);
        questionList2.add(question2);

        Question question3 = Question.builder()
                .title("Question3 title")
                .viewCount(5)
                .description("Question3 description")
                .user(userService.getByKey(4L))
                .isDeleted(false)
                .build();
        List<Tag> tagList3 = new ArrayList<>();
        tagList3.add(tagService.getByKey(2L));
        tagList3.add(tagService.getByKey(1L));
        question3.setTags(tagList3);
        questionList1.add(question3);
        questionList2.add(question3);

        Question question4 = Question.builder()
                .title("Question4 title")
                .viewCount(5)
                .description("Question4 description")
                .user(userService.getByKey(3L))
                .isDeleted(false)
                .build();
        List<Tag> tagList4 = new ArrayList<>();
        tagList4.add(tagService.getByKey(3L));
        question4.setTags(tagList4);
        questionList3.add(question4);

        Question question5 = Question.builder()
                .title("Question5 title")
                .viewCount(5)
                .description("Question5 description")
                .user(userService.getByKey(5L))
                .isDeleted(false)
                .build();
        List<Tag> tagList5 = new ArrayList<>();
        tagList5.add(tagService.getByKey(3L));
        question5.setTags(tagList5);
        questionList3.add(question5);

        Question question6 = Question.builder()
                .title("Question6 title")
                .viewCount(5)
                .description("Question6 description")
                .user(userService.getByKey(4L))
                .isDeleted(false)
                .build();
        List<Tag> tagList6 = new ArrayList<>();
        tagList6.add(tagService.getByKey(1L));
        tagList6.add(tagService.getByKey(2L));
        tagList6.add(tagService.getByKey(3L));
        question6.setTags(tagList6);
        questionList1.add(question6);
        questionList2.add(question6);
        questionList3.add(question6);

        Question question7 = Question.builder()
                .title("Question7 title")
                .viewCount(8)
                .description("Question7 description")
                .user(userService.getByKey(3L))
                .isDeleted(false)
                .build();
        List<Tag> tagList7 = new ArrayList<>();
        tagList7.add(tagService.getByKey(2L));
        tagList7.add(tagService.getByKey(3L));
        question7.setTags(tagList7);
        questionList2.add(question7);
        questionList3.add(question7);

        Question question8 = Question.builder()
                .title("Question8 title")
                .viewCount(7)
                .description("Question8 description")
                .user(userService.getByKey(2L))
                .isDeleted(false)
                .build();
        List<Tag> tagList8 = new ArrayList<>();
        tagList8.add(tagService.getByKey(3L));
        question8.setTags(tagList8);
        questionList3.add(question8);

        Question question9 = Question.builder()
                .title("Question9 title")
                .viewCount(9)
                .description("Question9 description")
                .user(userService.getByKey(5L))
                .isDeleted(false)
                .build();
        List<Tag> tagList9 = new ArrayList<>();
        tagList9.add(tagService.getByKey(3L));
        question9.setTags(tagList9);
        questionList1.add(question9);

        Question question10 = Question.builder()
                .title("Question10 title")
                .viewCount(10)
                .description("Question10 description")
                .user(userService.getByKey(3L))
                .isDeleted(false)
                .build();
        List<Tag> tagList10 = new ArrayList<>();
        tagList10.add(tagService.getByKey(1L));
        question10.setTags(tagList10);
        questionList2.add(question10);

        questionService.persist(question1);
        questionService.persist(question2);
        questionService.persist(question3);
        questionService.persist(question4);
        questionService.persist(question5);
        questionService.persist(question6);
        questionService.persist(question7);
        questionService.persist(question8);
        questionService.persist(question9);
        questionService.persist(question10);

        tagService.getByKey(1L).setQuestions(questionList1);
        tagService.getByKey(2L).setQuestions(questionList2);
        tagService.getByKey(3L).setQuestions(questionList3);

        VoteQuestion voteQuestion1 = new VoteQuestion(userService.getByKey(3L), question1, 1);
        VoteQuestion voteQuestion2 = new VoteQuestion(userService.getByKey(4L), question1, 1);
        VoteQuestion voteQuestion3 = new VoteQuestion(userService.getByKey(5L), question1, 1);
        VoteQuestion voteQuestion4 = new VoteQuestion(userService.getByKey(3L), question2, 1);
        VoteQuestion voteQuestion5 = new VoteQuestion(userService.getByKey(4L), question2, -1);
        VoteQuestion voteQuestion6 = new VoteQuestion(userService.getByKey(5L), question4, 1);
        VoteQuestion voteQuestion7 = new VoteQuestion(userService.getByKey(6L), question4, 1);
        VoteQuestion voteQuestion8 = new VoteQuestion(userService.getByKey(9L), question3, 1);
        VoteQuestion voteQuestion9 = new VoteQuestion(userService.getByKey(8L), question3, 1);
        VoteQuestion voteQuestion10 = new VoteQuestion(userService.getByKey(7L), question4, 1);

        voteQuestionService.persist(voteQuestion1);
        voteQuestionService.persist(voteQuestion2);
        voteQuestionService.persist(voteQuestion3);
        voteQuestionService.persist(voteQuestion4);
        voteQuestionService.persist(voteQuestion5);
        voteQuestionService.persist(voteQuestion6);
        voteQuestionService.persist(voteQuestion7);
        voteQuestionService.persist(voteQuestion8);
        voteQuestionService.persist(voteQuestion9);
        voteQuestionService.persist(voteQuestion10);
    }

    private void creatAnswerEntity() {
        Answer answer1_1 = Answer.builder()
                .user(userService.getByKey(3L))
                .isHelpful(true)
                .dateAcceptTime(LocalDateTime.now())
                .isDeleted(false)
                .question(questionService.getByKey(1L))
                .htmlBody("Helpful answer for question 1")
                .build();
        answerService.persist(answer1_1);

        Answer answer2_1 = Answer.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(1L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer2_1);

        Answer answer3_1 = Answer.builder()
                .user(userService.getByKey(4L))
                .question(questionService.getByKey(1L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer3_1);

        Answer answer1_2 = Answer.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(2L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer1_2);

        Answer answer2_2 = Answer.builder()
                .user(userService.getByKey(4L))
                .question(questionService.getByKey(2L))
                .isHelpful(true)
                .dateAcceptTime(LocalDateTime.now())
                .isDeleted(false)
                .htmlBody("Helpful answer for question 2")
                .build();
        answerService.persist(answer2_2);

        Answer answer1_3 = Answer.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(3L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer1_3);

        Answer answer2_3 = Answer.builder()
                .user(userService.getByKey(4L))
                .question(questionService.getByKey(3L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer2_3);

        Answer answer1_4 = Answer.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(4L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer1_4);

        Answer answer2_4 = Answer.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(4L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer2_4);

        Answer answer3_4 = Answer.builder()
                .user(userService.getByKey(4L))
                .question(questionService.getByKey(4L))
                .isHelpful(true)
                .dateAcceptTime(LocalDateTime.now())
                .isDeleted(false)
                .htmlBody("Helpful answer for question 4")
                .build();
        answerService.persist(answer3_4);

        AnswerVote answerVote1 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(9L))
                        .answer(answerService.getByKey(1L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote1);

        AnswerVote answerVote2 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(8L))
                        .answer(answerService.getByKey(1L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote2);

        AnswerVote answerVote3 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(7L))
                        .answer(answerService.getByKey(1L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote3);

        AnswerVote answerVote4 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(9L))
                        .answer(answerService.getByKey(2L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote4);

        AnswerVote answerVote5 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(8L))
                        .answer(answerService.getByKey(2L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote5);

        AnswerVote answerVote6 = AnswerVote.builder()
                .voteAnswerPK(AnswerVote.VoteAnswerPK.builder()
                        .persistDateTime(LocalDateTime.now())
                        .user(userService.getByKey(9L))
                        .answer(answerService.getByKey(3L))
                        .build())
                .vote(1)
                .build();
        answerVoteService.persist(answerVote6);

    }

    private void creatComment() {
        Comment comment1 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 1 text")
                .user(userService.getByKey(2L))
                .build();

        CommentAnswer commentAnswer = CommentAnswer.builder()
                .comment(comment1)
                .answer(answerService.getByKey(1L))
                .build();
        commentAnswerService.persist(commentAnswer);

        Comment comment2 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 2 text")
                .user(userService.getByKey(2L))
                .build();

        CommentAnswer commentAnswer2 = CommentAnswer.builder()
                .comment(comment2)
                .answer(answerService.getByKey(1L))
                .build();
        commentAnswerService.persist(commentAnswer2);

        Comment comment3 = Comment.builder()
                .commentType(CommentType.QUESTION)
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 3 text")
                .user(userService.getByKey(3L))
                .build();

        CommentQuestion commentQuestion1 = CommentQuestion.builder()
                .comment(comment3)
                .question(questionService.getByKey(1L))
                .build();
        commentQuestionService.persist(commentQuestion1);
    }

    private void creatUserFavoriteQuestion() {
        UserFavoriteQuestion userFavoriteQuestion = UserFavoriteQuestion.builder()
                .user(userService.getByKey(2L))
                .question(questionService.getByKey(2L))
                .build();
        userFavoriteQuestionService.persist(userFavoriteQuestion);
    }

    private void createBadges() {
        Badges badges1 = Badges.builder()
                .badges("Помощник")
                .description("Награждается если в день заработать 50 баллов")
                .reputationForMerit(50)
                .build();
        badgesService.persist(badges1);

        Badges badges = Badges.builder()
                .badges("Друг")
                .description("Награждается если в день заработать 100 баллов")
                .reputationForMerit(100)
                .build();
        badgesService.persist(badges);


        Badges badges2 = Badges.builder()
                .badges("Учитель")
                .description("Награждается если в день заработать 150 баллов")
                .reputationForMerit(150)
                .build();
        badgesService.persist(badges2);

        Badges badges3 = Badges.builder()
                .badges("Преподаватель")
                .description("Награждается если в день заработать 200 баллов")
                .reputationForMerit(200)
                .build();
        badgesService.persist(badges3);

        Badges badges4 = Badges.builder()
                .badges("Ментор")
                .description("Награждается если в день заработать 250 баллов")
                .reputationForMerit(250)
                .build();
        badgesService.persist(badges4);

        Badges badges7 = Badges.builder()
                .badges("JM Ментор")
                .description("Награждается если в день заработать 300 баллов")
                .reputationForMerit(300)
                .build();
        badgesService.persist(badges7);

        Badges badges5 = Badges.builder()
                .badges("Профессор")
                .description("Награждается если в день заработать 400 баллов")
                .reputationForMerit(400)
                .build();
        badgesService.persist(badges5);

        Badges badges6 = Badges.builder()
                .badges("Академик")
                .description("Награждается если в день заработать 500 баллов")
                .reputationForMerit(500)
                .build();
        badgesService.persist(badges6);

    }

    private void createUserBadges() {
        for (long i = 1; i < 12; i++) {
            for (long j = 1; j < 9; j++) {
                UserBadges user1Badges1 = UserBadges.builder()
                        .badges(badgesService.getByKey(j))
                        .user(userService.getByKey(i))
                        .ready(false)
                        .build();
                userBadgesService.persist(user1Badges1);
            }
        }
    }

    private void createReputation() {
        reputationService.updateOrInsert(userService.getByKey(1l), 15);
        reputationService.updateOrInsert(userService.getByKey(1l), 15);
    }
}
