package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.Badges;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.*;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
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

    private final UserService userService;
    private final CommentAnswerService commentAnswerService;
    private final CommentQuestionService commentQuestionService;
    private final TagService tagService;
    private final UserFavoriteQuestionService userFavoriteQuestionService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final RelatedTagService relatedTagService;
    private final ReputationService reputationService;
    private final BadgesService badgesService;
    private final UserBadgesService userBadgesService;
    private final VoteQuestionService voteQuestionService;
    private final EditorService editorService;
    private final ModeratorService moderatorService;
    private final VoteAnswerService voteAnswerService;


    @Autowired
    public TestDataEntityService(ReputationService reputationService,
                                 BadgesService badgesService,
                                 UserBadgesService userBadgesService,
                                 VoteQuestionService voteQuestionService,
                                 EditorService editorService,
                                 ModeratorService moderatorService,
                                 UserService userService,
                                 CommentAnswerService commentAnswerService,
                                 CommentQuestionService commentQuestionService,
                                 TagService tagService,
                                 UserFavoriteQuestionService userFavoriteQuestionService,
                                 QuestionService questionService,
                                 AnswerService answerService,
                                 RelatedTagService relatedTagService,
                                 VoteAnswerService voteAnswerService) {
        this.reputationService = reputationService;
        this.badgesService = badgesService;
        this.userBadgesService = userBadgesService;
        this.voteQuestionService = voteQuestionService;
        this.editorService = editorService;
        this.moderatorService = moderatorService;
        this.userService = userService;
        this.commentAnswerService = commentAnswerService;
        this.commentQuestionService = commentQuestionService;
        this.tagService = tagService;
        this.userFavoriteQuestionService = userFavoriteQuestionService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.relatedTagService = relatedTagService;
        this.voteAnswerService = voteAnswerService;
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
        creatReputation();
        creatEditor();
        creatModerator();
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
                .reputationCount(0)
                .city("Moscow")
                .linkSite("site.admin.ru")
                .linkGitHub("github.admin.ru")
                .linkVk("vk.admin.ru")
                .about("about admin")
                .role(adminRole)
                .isEnabled(true)
                .build();
        userService.persist(admin);

        Reputation reputation = Reputation.builder()
                .count(1)
                .user(admin)
                .build();
        reputationService.persist(reputation);

        User user1 = User.builder()
                .email("user1@user.ru")
                .password("user1")
                .fullName("Иван Иванович Иванов")
                .reputationCount(reputationCount)
                .fullName("Юрий Иванивич Пухов")
                .reputationCount(0)
                .city("Moscow")
                .linkSite("site.user1.ru")
                .linkGitHub("github.user1.ru")
                .linkVk("vk.user1.ru")
                .about("about user1")
                .role(userRole)
                .isEnabled(true)
                .build();
        userService.persist(user1);

        Reputation reputation1 = Reputation.builder()
                .count(1)
                .user(user1)
                .build();
        reputationService.persist(reputation1);

        User user2 = User.builder()
                .email("user2@user.ru")
                .isEnabled(true)
                .password("user2")
                .fullName("Петр2 Петрович2 Петров2")
                .reputationCount(reputationCount)
                .fullName("Петр Алексеевич Петров")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(userRole)
                .build();
        userService.persist(user2);

        Reputation reputation2 = Reputation.builder()
                .count(1)
                .user(user2)
                .build();
        reputationService.persist(reputation2);

        User user3 = User.builder()
                .email("user3@user.ru")
                .isEnabled(true)
                .password("user3")
                .fullName("Петр3 Петрович3 Петров3")
                .reputationCount(reputationCount)
                .fullName("Василий Дмитрьевич Петров")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user3.ru")
                .linkGitHub("github.user3.ru")
                .linkVk("vk.user3.ru")
                .about("about user3")
                .role(userRole)
                .build();
        userService.persist(user3);

        Reputation reputation3 = Reputation.builder()
                .count(1)
                .user(user3)
                .build();
        reputationService.persist(reputation3);

        User user4 = User.builder()
                .email("user4@user.ru")
                .isEnabled(true)
                .password("user4")
                .fullName("Петр4 Петрович4 Петров4")
                .reputationCount(reputationCount)
                .fullName("Евгений Петрович Суздальцев")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(userRole)
                .build();
        userService.persist(user4);

        Reputation reputation4 = Reputation.builder()
                .count(1)
                .user(user4)
                .build();
        reputationService.persist(reputation4);

        User user5 = User.builder()
                .email("user5@user.ru")
                .isEnabled(true)
                .password("user5")
                .fullName("Василий Петрович Гром")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user5.ru")
                .linkGitHub("github.user5.ru")
                .linkVk("vk.user5.ru")
                .about("about user5")
                .role(userRole)
                .build();
        userService.persist(user5);

        Reputation reputation5 = Reputation.builder()
                .count(1)
                .user(user5)
                .build();
        reputationService.persist(reputation5);

        User user6 = User.builder()
                .email("user6@user.ru")
                .isEnabled(true)
                .password("user6")
                .fullName("Георгий Андреевич Дымов")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user6.ru")
                .linkGitHub("github.user6.ru")
                .linkVk("vk.user6.ru")
                .about("about user6")
                .role(userRole)
                .build();
        userService.persist(user6);

        Reputation reputation6 = Reputation.builder()
                .count(1)
                .user(user6)
                .build();
        reputationService.persist(reputation6);

        User user7 = User.builder()
                .email("user7@user.ru")
                .isEnabled(true)
                .password("user7")
                .fullName("Роман Игоривич Смышляев")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user7.ru")
                .linkGitHub("github.user7.ru")
                .linkVk("vk.user7.ru")
                .about("about user7")
                .role(userRole)
                .build();
        userService.persist(user7);

        Reputation reputation7 = Reputation.builder()
                .count(16)
                .user(user7)
                .build();
        reputationService.persist(reputation7);

        User user8 = User.builder()
                .email("user8@user.ru")
                .isEnabled(true)
                .password("user8")
                .fullName("Алексей Петрович Пережёг")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user8.ru")
                .linkGitHub("github.user8.ru")
                .linkVk("vk.user8.ru")
                .about("about user8")
                .role(userRole)
                .build();
        userService.persist(user8);

        Reputation reputation8 = Reputation.builder()
                .count(1)
                .user(user8)
                .build();
        reputationService.persist(reputation8);

        User user9 = User.builder()
                .email("user9@user.ru")
                .isEnabled(true)
                .password("user9")
                .fullName("Андрей Евгеньевич Городилов")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user9.ru")
                .linkGitHub("github.user9.ru")
                .linkVk("vk.user9.ru")
                .about("about user9")
                .role(userRole)
                .build();
        userService.persist(user9);

        Reputation reputation9 = Reputation.builder()
                .count(1)
                .user(user9)
                .build();
        reputationService.persist(reputation9);

        User user10 = User.builder()
                .email("user10@user.ru")
                .isEnabled(true)
                .password("user10")
                .fullName("Давид Антонович Чехов")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user10.ru")
                .linkGitHub("github.user10.ru")
                .linkVk("vk.user10.ru")
                .about("about user10")
                .role(userRole)
                .build();
        userService.persist(user10);

        Reputation reputation10 = Reputation.builder()
                .count(1)
                .user(user10)
                .build();
        reputationService.persist(reputation10);

        User user11 = User.builder()
                .email("user11@user.ru")
                .isEnabled(true)
                .password("user11")
                .fullName("Семён Павлович Слепаков")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user11.ru")
                .linkGitHub("github.user11.ru")
                .linkVk("vk.user11.ru")
                .about("about user11")
                .role(userRole)
                .build();
        userService.persist(user11);

        Reputation reputation11 = Reputation.builder()
                .count(1)
                .user(user11)
                .build();
        reputationService.persist(reputation11);

        User user12 = User.builder()
                .email("user12@user.ru")
                .isEnabled(true)
                .password("user12")
                .fullName("Иван Дмитревич Кузмин")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user12.ru")
                .linkGitHub("github.user12.ru")
                .linkVk("vk.user12.ru")
                .about("about user12")
                .role(userRole)
                .build();
        userService.persist(user12);

        Reputation reputation12 = Reputation.builder()
                .count(1)
                .user(user12)
                .build();
        reputationService.persist(reputation12);

        User user13 = User.builder()
                .email("user13@user.ru")
                .isEnabled(true)
                .password("user13")
                .fullName("Владимер Романович Кержаков")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user13.ru")
                .linkGitHub("github.user13.ru")
                .linkVk("vk.user13.ru")
                .about("about user13")
                .role(userRole)
                .build();
        userService.persist(user13);

        Reputation reputation13 = Reputation.builder()
                .count(1)
                .user(user13)
                .build();
        reputationService.persist(reputation13);

        User user14 = User.builder()
                .email("user14@user.ru")
                .isEnabled(true)
                .password("user14")
                .fullName("Иван Николаевич Курочкин")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user14.ru")
                .linkGitHub("github.user14.ru")
                .linkVk("vk.user14.ru")
                .about("about user14")
                .role(userRole)
                .build();
        userService.persist(user14);

        Reputation reputation14 = Reputation.builder()
                .count(1)
                .user(user14)
                .build();
        reputationService.persist(reputation14);

        User user15 = User.builder()
                .email("user15@user.ru")
                .isEnabled(true)
                .password("user15")
                .fullName("Андрей Александрович Яращук")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user15.ru")
                .linkGitHub("github.user15.ru")
                .linkVk("vk.user15.ru")
                .about("about user15")
                .role(userRole)
                .build();
        userService.persist(user15);

        Reputation reputation15 = Reputation.builder()
                .count(1)
                .user(user15)
                .build();
        reputationService.persist(reputation15);

        User user16 = User.builder()
                .email("user16@user.ru")
                .isEnabled(true)
                .password("user16")
                .fullName("Алексей Борисович Огородов")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user16.ru")
                .linkGitHub("github.user16.ru")
                .linkVk("vk.user16.ru")
                .about("about user16")
                .role(userRole)
                .build();
        userService.persist(user16);

        Reputation reputation16 = Reputation.builder()
                .count(1)
                .user(user16)
                .build();
        reputationService.persist(reputation16);

        User user17 = User.builder()
                .email("user17@user.ru")
                .isEnabled(true)
                .password("user17")
                .fullName("Артем Романович Туборг")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user17.ru")
                .linkGitHub("github.user17.ru")
                .linkVk("vk.user17.ru")
                .about("about user17")
                .role(userRole)
                .build();
        userService.persist(user17);

        Reputation reputation17 = Reputation.builder()
                .count(1)
                .user(user17)
                .build();
        reputationService.persist(reputation17);

        User user18 = User.builder()
                .email("user18@user.ru")
                .isEnabled(true)
                .password("user18")
                .fullName("Алексей Алексеевич Мышкин")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user18.ru")
                .linkGitHub("github.user18.ru")
                .linkVk("vk.user18.ru")
                .about("about user18")
                .role(userRole)
                .build();
        userService.persist(user18);

        Reputation reputation18 = Reputation.builder()
                .count(1)
                .user(user18)
                .build();
        reputationService.persist(reputation18);

        User user19 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user19")
                .role(userRole)
                .build();
        userService.persist(user19);

        Reputation reputation19 = Reputation.builder()
                .count(1)
                .user(user19)
                .build();
        reputationService.persist(reputation19);

        User user20 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Сергей Васильевич Ким")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user19.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user20);

        Reputation reputation20 = Reputation.builder()
                .count(2)
                .user(user20)
                .build();
        reputationService.persist(reputation20);

        User user21 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user21")
                .fullName("Сергей Мельб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user21")
                .role(userRole)
                .build();
        userService.persist(user21);

        Reputation reputation21 = Reputation.builder()
                .count(3)
                .user(user21)
                .build();
        reputationService.persist(reputation21);

        User user22 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user22")
                .role(userRole)
                .build();
        userService.persist(user22);

        Reputation reputation22 = Reputation.builder()
                .count(1)
                .user(user22)
                .build();
        reputationService.persist(reputation22);

        User user23 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Генадий Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user23")
                .role(userRole)
                .build();
        userService.persist(user23);

        Reputation reputation23 = Reputation.builder()
                .count(1)
                .user(user23)
                .build();
        reputationService.persist(reputation23);

        User user24 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Денис Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user24")
                .role(userRole)
                .build();
        userService.persist(user24);

        Reputation reputation24 = Reputation.builder()
                .count(1)
                .user(user24)
                .build();
        reputationService.persist(reputation24);

        User user25 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Дмитрий Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user25")
                .role(userRole)
                .build();
        userService.persist(user25);

        Reputation reputation25 = Reputation.builder()
                .count(1)
                .user(user25)
                .build();
        reputationService.persist(reputation25);

        User user26 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user26")
                .role(userRole)
                .build();
        userService.persist(user26);

        Reputation reputation26 = Reputation.builder()
                .count(1)
                .user(user26)
                .build();
        reputationService.persist(reputation26);

        User user28 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user28")
                .role(userRole)
                .build();
        userService.persist(user28);

        Reputation reputation28 = Reputation.builder()
                .count(1)
                .user(user28)
                .build();
        reputationService.persist(reputation28);

        User user29 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user29")
                .role(userRole)
                .build();
        userService.persist(user29);

        Reputation reputation29 = Reputation.builder()
                .count(5)
                .user(user29)
                .build();
        reputationService.persist(reputation29);

        User user30 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user30")
                .role(userRole)
                .build();
        userService.persist(user30);

        Reputation reputation30 = Reputation.builder()
                .count(1)
                .user(user30)
                .build();
        reputationService.persist(reputation30);

        User user31 = User.builder()
                .email("user31@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Максим Петрович Старадуб")
                .reputationCount(0)
                .city("SPB")
                .linkSite("site.user31.ru")
                .linkGitHub("github.user31.ru")
                .linkVk("vk.user31.ru")
                .about("about user31")
                .role(userRole)
                .build();
        userService.persist(user31);

        Reputation reputation31 = Reputation.builder()
                .count(13)
                .user(user31)
                .build();
        reputationService.persist(reputation31);

    }

    private void creatReputation() {
        Reputation reputation20 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(4L))
                .build();
        reputationService.persist(reputation20);

        Reputation reputation21 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(4L))
                .build();
        reputationService.persist(reputation21);

        Reputation reputation22 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(7L))
                .build();
        reputationService.persist(reputation22);

        Reputation reputation23 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(8L))
                .build();
        reputationService.persist(reputation23);

        Reputation reputation24 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(8L))
                .build();
        reputationService.persist(reputation24);

        Reputation reputation25 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(10L))
                .build();
        reputationService.persist(reputation25);

        Reputation reputation26 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(10L))
                .build();
        reputationService.persist(reputation26);

        Reputation reputation27 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(12L))
                .build();
        reputationService.persist(reputation27);

        Reputation reputation28 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(12L))
                .build();
        reputationService.persist(reputation28);

        Reputation reputation29 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(12L))
                .build();
        reputationService.persist(reputation29);

        Reputation reputation30 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(15L))
                .build();
        reputationService.persist(reputation30);

        Reputation reputation31 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(15L))
                .build();
        reputationService.persist(reputation31);

        Reputation reputation32 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(17L))
                .build();
        reputationService.persist(reputation32);

        Reputation reputation33 = Reputation.builder()
                .count(2)
                .user(userService.getByKey(18L))
                .build();
        reputationService.persist(reputation33);

        Reputation reputation34 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(19L))
                .build();
        reputationService.persist(reputation34);

        Reputation reputation35 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(19L))
                .build();
        reputationService.persist(reputation35);

        Reputation reputation36 = Reputation.builder()
                .count(3)
                .user(userService.getByKey(22L))
                .build();
        reputationService.persist(reputation36);

        Reputation reputation37 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(25L))
                .build();
        reputationService.persist(reputation37);

        Reputation reputation38 = Reputation.builder()
                .count(2)
                .user(userService.getByKey(2L))
                .build();
        reputationService.persist(reputation38);

        Reputation reputation39 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(26L))
                .build();
        reputationService.persist(reputation39);

        Reputation reputation40 = Reputation.builder()
                .count(1)
                .user(userService.getByKey(30L))
                .build();
        reputationService.persist(reputation40);
    }

    private void creatEditor() {
        Editor editor = Editor.builder()
                .count(1)
                .user(userService.getByKey(1L))
                .build();
        editorService.persist(editor);

        Editor editor1 = Editor.builder()
                .count(2)
                .user(userService.getByKey(4L))
                .build();
        editorService.persist(editor1);

        Editor editor2 = Editor.builder()
                .count(3)
                .user(userService.getByKey(5L))
                .build();
        editorService.persist(editor2);

        Editor editor3 = Editor.builder()
                .count(4)
                .user(userService.getByKey(6L))
                .build();
        editorService.persist(editor3);

        Editor editor4 = Editor.builder()
                .count(5)
                .user(userService.getByKey(6L))
                .build();
        editorService.persist(editor4);

        Editor editor5 = Editor.builder()
                .count(6)
                .user(userService.getByKey(9L))
                .build();
        editorService.persist(editor5);

        Editor editor6 = Editor.builder()
                .count(7)
                .user(userService.getByKey(9L))
                .build();
        editorService.persist(editor6);

        Editor editor7 = Editor.builder()
                .count(8)
                .user(userService.getByKey(11L))
                .build();
        editorService.persist(editor7);

        Editor editor8 = Editor.builder()
                .count(7)
                .user(userService.getByKey(11L))
                .build();
        editorService.persist(editor8);

        Editor editor9 = Editor.builder()
                .count(9)
                .user(userService.getByKey(13L))
                .build();
        editorService.persist(editor9);

        Editor editor10 = Editor.builder()
                .count(4)
                .user(userService.getByKey(13L))
                .build();
        editorService.persist(editor10);

        Editor editor11 = Editor.builder()
                .count(3)
                .user(userService.getByKey(14L))
                .build();
        editorService.persist(editor11);

        Editor editor12 = Editor.builder()
                .count(1)
                .user(userService.getByKey(14L))
                .build();
        editorService.persist(editor12);

        Editor editor13 = Editor.builder()
                .count(2)
                .user(userService.getByKey(15L))
                .build();
        editorService.persist(editor13);

        Editor editor14 = Editor.builder()
                .count(7)
                .user(userService.getByKey(15L))
                .build();
        editorService.persist(editor14);

        Editor editor15 = Editor.builder()
                .count(9)
                .user(userService.getByKey(16L))
                .build();
        editorService.persist(editor15);

        Editor editor16 = Editor.builder()
                .count(5)
                .user(userService.getByKey(17L))
                .build();
        editorService.persist(editor16);

        Editor editor17 = Editor.builder()
                .count(1)
                .user(userService.getByKey(17L))
                .build();
        editorService.persist(editor17);

        Editor editor18 = Editor.builder()
                .count(4)
                .user(userService.getByKey(18L))
                .build();
        editorService.persist(editor18);

        Editor editor19 = Editor.builder()
                .count(2)
                .user(userService.getByKey(19L))
                .build();
        editorService.persist(editor19);
    }

    private void creatModerator() {
        Moderator moderator = Moderator.builder()
                .user(userService.getByKey(2L))
                .build();
        moderatorService.persist(moderator);

        Moderator moderator1 = Moderator.builder()
                .user(userService.getByKey(8L))
                .build();
        moderatorService.persist(moderator1);

        Moderator moderator2 = Moderator.builder()
                .user(userService.getByKey(15L))
                .build();
        moderatorService.persist(moderator2);
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

//         questionList1.add(questionService.getByKey(1L));

        List<Question> questionList2 = new ArrayList<>();
        List<Question> questionList3 = new ArrayList<>();

        Question question1 = Question.builder()
                .title("Question1 title")
                .viewCount(3)
                .description("Question1 description")
                .user(userService.getByKey(1L))
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
                .user(userService.getByKey(2L))
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
                .user(userService.getByKey(3L))
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
                .user(userService.getByKey(4L))
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
                .user(userService.getByKey(4L))
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
                .user(userService.getByKey(6L))
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
                .user(userService.getByKey(7L))
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
                .user(userService.getByKey(8L))
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
                .user(userService.getByKey(8L))
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
                .user(userService.getByKey(10L))
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
        VoteQuestion voteQuestion3 = new VoteQuestion(userService.getByKey(5L), question2, 1);
//        VoteQuestion voteQuestion4 = new VoteQuestion(userService.getByKey(3L), question3, 1);
        VoteQuestion voteQuestion5 = new VoteQuestion(userService.getByKey(4L), question2, 1);
        VoteQuestion voteQuestion6 = new VoteQuestion(userService.getByKey(5L), question4, 1);
        VoteQuestion voteQuestion7 = new VoteQuestion(userService.getByKey(6L), question4, 1);
        VoteQuestion voteQuestion8 = new VoteQuestion(userService.getByKey(9L), question5, 1);
        VoteQuestion voteQuestion9 = new VoteQuestion(userService.getByKey(8L), question6, 1);


        voteQuestionService.persist(voteQuestion1);
        voteQuestionService.persist(voteQuestion2);
        voteQuestionService.persist(voteQuestion3);
//        voteQuestionService.persist(voteQuestion4);
        voteQuestionService.persist(voteQuestion5);
        voteQuestionService.persist(voteQuestion6);
        voteQuestionService.persist(voteQuestion7);
        voteQuestionService.persist(voteQuestion8);
        voteQuestionService.persist(voteQuestion9);
    }

    private void creatAnswerEntity() {
        Answer answer1_1 = Answer.builder()
                .user(userService.getByKey(3L))
                .countValuable(0)
                .isHelpful(true)
                .isDeleted(false)
                .question(questionService.getByKey(1L))
                .htmlBody("Helpful answer for question 1")
                .build();
        answerService.persist(answer1_1);

        Answer answer1_2 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(0)
                .question(questionService.getByKey(1L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer1_2);

        Answer answer2_1 = Answer.builder()
                .user(userService.getByKey(6L))
                .countValuable(0)
                .question(questionService.getByKey(2L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer2_1);

        Answer answer3_1 = Answer.builder()
                .user(userService.getByKey(5L))
                .countValuable(0)
                .question(questionService.getByKey(3L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer3_1);

        Answer answer3_2 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(0)
                .question(questionService.getByKey(3L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer3_2);

        Answer answer4_1 = Answer.builder()
                .user(userService.getByKey(4L))
                .countValuable(0)
                .question(questionService.getByKey(4L))
                .isHelpful(true)
                .isDeleted(false)
                .htmlBody("Helpful answer for question 4")
                .build();
        answerService.persist(answer4_1);

        Answer answer5_1 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(0)
                .question(questionService.getByKey(5L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 5")
                .build();
        answerService.persist(answer5_1);

        Answer answer5_2 = Answer.builder()
                .user(userService.getByKey(4L))
                .countValuable(0)
                .question(questionService.getByKey(5L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 5")
                .build();
        answerService.persist(answer5_2);

        Answer answer5_3 = Answer.builder()
                .user(userService.getByKey(3L))
                .countValuable(0)
                .question(questionService.getByKey(5L))
                .isHelpful(false)
                .isDeleted(false)
                .htmlBody("Don't helpful answer for question 5")
                .build();
        answerService.persist(answer5_3);

        Answer answer6_1 = Answer.builder()
                .user(userService.getByKey(7L))
                .countValuable(0)
                .question(questionService.getByKey(6L))
                .isHelpful(true)
                .isDeleted(false)
                .htmlBody("Helpful answer for question 6")
                .build();
        answerService.persist(answer6_1);

        VoteAnswer voteAnswer = new VoteAnswer(userService.getByKey(2L), answer1_1, 1);
        VoteAnswer voteAnswer1 = new VoteAnswer(userService.getByKey(5L), answer1_2, 1);
        VoteAnswer voteAnswer2 = new VoteAnswer(userService.getByKey(3L), answer2_1, 1);
        VoteAnswer voteAnswer3 = new VoteAnswer(userService.getByKey(4L), answer3_1, 1);
        VoteAnswer voteAnswer4 = new VoteAnswer(userService.getByKey(7L), answer3_2, 1);
        VoteAnswer voteAnswer5 = new VoteAnswer(userService.getByKey(6L), answer4_1, 1);
        VoteAnswer voteAnswer6 = new VoteAnswer(userService.getByKey(1L), answer5_1, 1);
        VoteAnswer voteAnswer7 = new VoteAnswer(userService.getByKey(8L), answer5_2, 1);
        VoteAnswer voteAnswer8 = new VoteAnswer(userService.getByKey(9L), answer5_3, 1);
        VoteAnswer voteAnswer9 = new VoteAnswer(userService.getByKey(5L), answer2_1, 1);
        VoteAnswer voteAnswer10 = new VoteAnswer(userService.getByKey(7L), answer2_1, 1);
        VoteAnswer voteAnswer11 = new VoteAnswer(userService.getByKey(10L), answer5_1, 1);

        voteAnswerService.persist(voteAnswer);
        voteAnswerService.persist(voteAnswer1);
        voteAnswerService.persist(voteAnswer2);
        voteAnswerService.persist(voteAnswer3);
        voteAnswerService.persist(voteAnswer4);
        voteAnswerService.persist(voteAnswer5);
        voteAnswerService.persist(voteAnswer6);
        voteAnswerService.persist(voteAnswer7);
        voteAnswerService.persist(voteAnswer8);
        voteAnswerService.persist(voteAnswer9);
        voteAnswerService.persist(voteAnswer10);
        voteAnswerService.persist(voteAnswer11);

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

    private void createReputation(){
        reputationService.updateOrInsert(userService.getByKey(1l), 15);
        reputationService.updateOrInsert(userService.getByKey(1l), 15);
    }
}
