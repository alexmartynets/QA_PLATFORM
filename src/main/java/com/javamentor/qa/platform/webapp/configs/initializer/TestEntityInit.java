package com.javamentor.qa.platform.webapp.configs.initializer;

import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Builder
public class TestEntityInit {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestEntityInit.class);

//    @Autowired
//    private Answer answer;
//
//    @Autowired
//    private CommentAnswer commentAnswer;
//
//    @Autowired
//    private Question question;
//
//    @Autowired
//    private CommentQuestion commentQuestion;
//
//    @Autowired
//    private Tag tag;
//
//    @Autowired
//    private RelatedTag relatedTag;

//    @Autowired
//    private Role role;

//    @Autowired
//    private User user;

//    @Autowired
//    private UserFavoriteQuestion userFavoriteQuestion;
//
//    @Autowired
//    private Comment comment;

    private void init() throws Exception {

        // Создаем роли
        Role roleAdmin = Role.builder()
                .id(1L)
                .name("ADMIN")
                .build();

        Role roleUser = Role.builder()
                .id(2L)
                .name("USER")
                .build();

        // содаем и добавляем в БД регистрационные шаги для пользователей
//        RegistrationStep rs1 = new RegistrationStep();
//        rs1.setName("registration-step-user");
//        registrationStepService.save(rs1);
//
//        RegistrationStep rs2 = new RegistrationStep();
//        rs2.setName("registration-step-company");
//        registrationStepService.save(rs2);
//
//        RegistrationStep rs3 = new RegistrationStep();
//        rs3.setName("registration-step-address");
//        registrationStepService.save(rs3);
//
//        RegistrationStep rsFinal = new RegistrationStep();
//        rsFinal.setName("registration-step-end");
//        registrationStepService.save(rsFinal);

//        private Long id;
//
//        private String email;
//
//        private String password;
//
//        private String fullName;
//
//        private LocalDateTime persistDateTime;
//
//        private Boolean isEnabled = true;
//
//        private Integer reputationCount;
//
//        private String city;
//
//        private String linkSite;
//
//        private String linkGitHub;
//
//        private String linkVk;
//
//        private String about;
//
//        private Blob imageUser;
//
//        private LocalDateTime lastUpdateDateTime;
//
//        private Role role;

        // тестовые аккаунты: admin 1
        User admin = User.builder()
                .id(1L)
                .email("admin@admin.ru")
                .password("admin")
                .fullName("Админ Админович Админов")
                .reputationCount(5)
                .city("Moscow")
                .linkSite("site.admin.ru")
                .linkGitHub("github.admin.ru")
                .linkVk("vk.admin.ru")
                .about("about admin")
                .role(roleAdmin)
                .build();

        // тестовые аккаунты: user 1
        User user1 = User.builder()
                .id(2L)
                .email("user1@user.ru")
                .password("user1")
                .fullName("Иван Иванович Иванов")
                .reputationCount(4)
                .city("Moscow")
                .linkSite("site.user1.ru")
                .linkGitHub("github.user1.ru")
                .linkVk("vk.user1.ru")
                .about("about user1")
                .role(roleUser)
                .build();

        // тестовые аккаунты: user 2
        User user2 = User.builder()
                .id(3L)
                .email("user2@user.ru")
                .password("user2")
                .fullName("Петр Петрович Петров")
                .reputationCount(3)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(roleAdmin)
                .build();

        // тестовые сущности: tag 1
        Tag tag1 = Tag.builder()
                .id(1L)
                .name("Main tag1")
                .description("Description tag1")
                .persistDateTime(LocalDateTime.now())
                .build();

        // тестовые сущности: tag 2
        Tag tag2 = Tag.builder()
                .id(2L)
                .name("Main tag2")
                .description("Description tag2")
                .persistDateTime(LocalDateTime.now())
                .build();

        // тестовые сущности: question 1
        Question question1 = Question.builder()
                .id(1L)
                .title("Question1 title")
                .viewCount(3)
                .description("Question1 description")
                .persistDateTime(LocalDateTime.now())
                .user(user1)
                .countValuable(2)
                .build();

        // создание листа с тэгами для question1
        List<Tag> tagList1 = new ArrayList<>();
        tagList1.add(tag1);
        tagList1.add(tag2);
        question1.setTags(tagList1);

        // тестовые сущности: question 1
        Question question2 = Question.builder()
                .id(2L)
                .title("Question2 title")
                .viewCount(4)
                .description("Question2 description")
                .persistDateTime(LocalDateTime.now())
                .user(user2)
                .countValuable(2)
                .build();
        List<Tag> tagList2 = new ArrayList<>();
        tagList2.add(tag2);
        question2.setTags(tagList2);

        // создание списка вопросов для тэга1
        List<Question> questionList1 = new ArrayList<>();
        questionList1.add(question1);
        tag1.setQuestions(questionList1);

        // создание списка вопросов для тэга2
        List<Question> questionList2 = new ArrayList<>();
        questionList2.add(question1);
        questionList2.add(question2);
        tag2.setQuestions(questionList2);

        //todo не нашёл id пользователя - автора ответа

        // создание ответов для question1
        Answer answer1_1 = Answer.builder()
                .id(1L)
                .countValuable(2)
                .isHelpful(true)
                .question(question1)
                .dateAcceptTime(LocalDateTime.now())
                .htmlBody("Helpfull answer for question 1")
                .build();

        // создание ответов для question1
        Answer answer1_2 = Answer.builder()
                .id(2L)
                .countValuable(2)
                .question(question1)
                .dateAcceptTime(LocalDateTime.now())
                .htmlBody("Don't helpfull answer for question 1")
                .build();


//        admin.("admin");
//        admin.setPassword("admin");
//        admin.setEmail("admin@gmail.com");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(roleAdmin);
//        adminRoles.add(roleUser);
//        admin.setRoles(adminRoles);
//        userService.addUser(admin);

    }

}
