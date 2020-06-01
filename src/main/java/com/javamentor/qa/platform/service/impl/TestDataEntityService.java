package com.javamentor.qa.platform.service.impl;

import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
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

    public void createEntity() {
        creatUserEntity();
        creatTagEntity();
        creatQuestionEntity();
        creatAnswerEntity();
        creatComment();
        creatUserFavoriteQuestion();
    }

    private void creatUserEntity() {

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
                .reputationCount(5)
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
                .fullName("Юрий Иванивич Пухов")
                .reputationCount(4)
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
                .fullName("Петр Алексеевич Петров")
                .reputationCount(1)
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
                .fullName("Василий Дмитрьевич Петров")
                .reputationCount(19)
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
                .fullName("Евгений Петрович Суздальцев")
                .reputationCount(6)
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
                .fullName("Василий Петрович Гром")
                .reputationCount(21)
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
                .fullName("Георгий Андреевич Дымов")
                .reputationCount(13)
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
                .fullName("Роман Игоривич Смышляев")
                .reputationCount(35)
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
                .fullName("Алексей Петрович Пережёг")
                .reputationCount(2)
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
                .fullName("Андрей Евгеньевич Городилов")
                .reputationCount(33)
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
                .fullName("Давид Антонович Чехов")
                .reputationCount(43)
                .city("SPB")
                .linkSite("site.user10.ru")
                .linkGitHub("github.user10.ru")
                .linkVk("vk.user10.ru")
                .about("about user10")
                .role(userRole)
                .build();
        userService.persist(user10);

        User user11 = User.builder()
                .email("user11@user.ru")
                .isEnabled(true)
                .password("user11")
                .fullName("Семён Павлович Слепаков")
                .reputationCount(23)
                .city("SPB")
                .linkSite("site.user11.ru")
                .linkGitHub("github.user11.ru")
                .linkVk("vk.user11.ru")
                .about("about user11")
                .role(userRole)
                .build();
        userService.persist(user11);

        User user12 = User.builder()
                .email("user12@user.ru")
                .isEnabled(true)
                .password("user12")
                .fullName("Иван Дмитревич Кузмин")
                .reputationCount(14)
                .city("SPB")
                .linkSite("site.user12.ru")
                .linkGitHub("github.user12.ru")
                .linkVk("vk.user12.ru")
                .about("about user12")
                .role(userRole)
                .build();
        userService.persist(user12);

        User user13 = User.builder()
                .email("user13@user.ru")
                .isEnabled(true)
                .password("user13")
                .fullName("Владимер Романович Кержаков")
                .reputationCount(9)
                .city("SPB")
                .linkSite("site.user13.ru")
                .linkGitHub("github.user13.ru")
                .linkVk("vk.user13.ru")
                .about("about user13")
                .role(userRole)
                .build();
        userService.persist(user13);

        User user14 = User.builder()
                .email("user14@user.ru")
                .isEnabled(true)
                .password("user14")
                .fullName("Иван Николаевич Курочкин")
                .reputationCount(11)
                .city("SPB")
                .linkSite("site.user14.ru")
                .linkGitHub("github.user14.ru")
                .linkVk("vk.user14.ru")
                .about("about user14")
                .role(userRole)
                .build();
        userService.persist(user14);

        User user15 = User.builder()
                .email("user15@user.ru")
                .isEnabled(true)
                .password("user15")
                .fullName("Андрей Александрович Яращук")
                .reputationCount(17)
                .city("SPB")
                .linkSite("site.user15.ru")
                .linkGitHub("github.user15.ru")
                .linkVk("vk.user15.ru")
                .about("about user15")
                .role(userRole)
                .build();
        userService.persist(user15);

        User user16 = User.builder()
                .email("user16@user.ru")
                .isEnabled(true)
                .password("user16")
                .fullName("Алексей Борисович Огородов")
                .reputationCount(18)
                .city("SPB")
                .linkSite("site.user16.ru")
                .linkGitHub("github.user16.ru")
                .linkVk("vk.user16.ru")
                .about("about user16")
                .role(userRole)
                .build();
        userService.persist(user16);

        User user17 = User.builder()
                .email("user17@user.ru")
                .isEnabled(true)
                .password("user17")
                .fullName("Артем Романович Туборг")
                .reputationCount(36)
                .city("SPB")
                .linkSite("site.user17.ru")
                .linkGitHub("github.user17.ru")
                .linkVk("vk.user17.ru")
                .about("about user17")
                .role(userRole)
                .build();
        userService.persist(user17);

        User user18 = User.builder()
                .email("user18@user.ru")
                .isEnabled(true)
                .password("user18")
                .fullName("Алексей Алексеевич Мышкин")
                .reputationCount(39)
                .city("SPB")
                .linkSite("site.user18.ru")
                .linkGitHub("github.user18.ru")
                .linkVk("vk.user18.ru")
                .about("about user18")
                .role(userRole)
                .build();
        userService.persist(user18);

        User user19 = User.builder()
                .email("user19@user.ru")
                .isEnabled(true)
                .password("user19")
                .fullName("Сергей Петрович Старадуб")
                .reputationCount(53)
                .city("SPB")
                .linkSite("site.user19.ru")
                .linkGitHub("github.user19.ru")
                .linkVk("vk.user19.ru")
                .about("about user19")
                .role(userRole)
                .build();
        userService.persist(user19);

        User user20 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(63)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user20);

        User user21 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(73)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user21);

        User user22 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(76)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user22);

        User user23 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(71)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user23);

        User user24 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(76)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user24);

        User user25 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(78)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user25);

        User user26 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(79)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user26);

        User user27 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(81)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user27);

        User user28 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(83)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user28);

        User user41 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(85)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user41);

        User user29 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(88)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user29);

        User user30 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(90)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user30);

        User user31 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(103)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user31);

        User user32 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(113)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user32);

        User user33 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(133)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user33);

        User user34 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(47)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user34);

        User user35 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(333)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user35);

        User user36 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(163)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user36);

        User user37 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(603)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user37);

        User user38 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(11)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user38);

        User user39 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(121)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user39);

        User user40 = User.builder()
                .email("user20@user.ru")
                .isEnabled(true)
                .password("user20")
                .fullName("Никокай Олегович Жук")
                .reputationCount(101)
                .city("SPB")
                .linkSite("site.user20.ru")
                .linkGitHub("github.user20.ru")
                .linkVk("vk.user20.ru")
                .about("about user20")
                .role(userRole)
                .build();
        userService.persist(user40);

    }

    private void creatTagEntity() {
        Tag tag1 = Tag.builder()
                .name("Java")
                .description("Description tag1")
                .build();
        tagService.persist(tag1);

        Tag tag2 = Tag.builder()
                .name("Python")
                .description("Description tag2")
                .build();
        tagService.persist(tag2);

        Tag tag3 = Tag.builder()
                .name("SQL")
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
                .user(userService.getByKey(2L))
                .countValuable(2)
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
                .countValuable(2)
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
                .countValuable(3)
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
                .countValuable(3)
                .build();
        List<Tag> tagList4 = new ArrayList<>();
        tagList4.add(tagService.getByKey(3L));
        question4.setTags(tagList4);
        questionList3.add(question4);

        Question question5 = Question.builder()
                .title("Question5 title")
                .viewCount(5)
                .description("Question5 description")
                .user(userService.getByKey(3L))
                .countValuable(3)
                .build();
        List<Tag> tagList5 = new ArrayList<>();
        tagList5.add(tagService.getByKey(3L));
        question5.setTags(tagList5);
        questionList3.add(question5);

        Question question6 = Question.builder()
                .title("Question6 title")
                .viewCount(5)
                .description("Question6 description")
                .user(userService.getByKey(3L))
                .countValuable(6)
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
                .countValuable(3)
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
                .user(userService.getByKey(3L))
                .countValuable(3)
                .build();
        List<Tag> tagList8 = new ArrayList<>();
        tagList8.add(tagService.getByKey(3L));
        question8.setTags(tagList8);
        questionList3.add(question8);

        Question question9 = Question.builder()
                .title("Question9 title")
                .viewCount(9)
                .description("Question9 description")
                .user(userService.getByKey(3L))
                .countValuable(3)
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
                .countValuable(3)
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
    }

    private void creatAnswerEntity() {
        Answer answer1_1 = Answer.builder()
                .user(userService.getByKey(3L))
                .countValuable(2)
                .isHelpful(true)
                .question(questionService.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .htmlBody("Helpful answer for question 1")
                .build();
        answerService.persist(answer1_1);

        Answer answer1_2 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer1_2);

        Answer answer1_3 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .persistDateTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer1_3);

        Answer answer1_4 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 1")
                .build();
        answerService.persist(answer1_4);

        Answer answer2_1 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(2L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer2_1);

        Answer answer2_2 = Answer.builder()
                .user(userService.getByKey(4L))
                .countValuable(2)
                .question(questionService.getByKey(2L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(true)
                .htmlBody("Helpful answer for question 2")
                .build();
        answerService.persist(answer2_2);

        Answer answer2_3 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(2L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 2")
                .build();
        answerService.persist(answer2_3);

        Answer answer3_1 = Answer.builder()
                .user(userService.getByKey(4L))
                .countValuable(2)
                .question(questionService.getByKey(3L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer3_1);

        Answer answer3_2 = Answer.builder()
                .user(userService.getByKey(2L))
                .countValuable(2)
                .question(questionService.getByKey(3L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(true)
                .htmlBody("Don't helpful answer for question 3")
                .build();
        answerService.persist(answer3_2);

        Answer answer4_1 = Answer.builder()
                .user(userService.getByKey(4L))
                .countValuable(2)
                .question(questionService.getByKey(4L))
                .dateAcceptTime(LocalDateTime.now())
                .isHelpful(true)
                .htmlBody("Helpful answer for question 4")
                .build();
        answerService.persist(answer4_1);

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
}
