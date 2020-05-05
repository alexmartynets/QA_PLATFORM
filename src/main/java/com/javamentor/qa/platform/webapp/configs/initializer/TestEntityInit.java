package com.javamentor.qa.platform.webapp.configs.initializer;

import com.javamentor.qa.platform.dao.impl.model.testImpl.Comment.TestClassComment;
import com.javamentor.qa.platform.dao.impl.model.testImpl.Comment.TestClassCommentAnswer;
import com.javamentor.qa.platform.dao.impl.model.testImpl.*;
import com.javamentor.qa.platform.dao.impl.model.testImpl.Comment.TestClassCommentQuestion;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.UserFavoriteQuestion;
import com.javamentor.qa.platform.service.impl.RoleServiceTest;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Builder
public class TestEntityInit {

    private static Logger log = Logger.getLogger(TestEntityInit.class.getName());

    @Autowired
    private final TestClassRole testClassRole;

    @Autowired
    private final TestClassUser testClassUser;

    @Autowired
    private final TestClassQuestion testClassQuestion;

    @Autowired
    private final TestClassAnswer testClassAnswer;

    @Autowired
    private final TestClassComment testClassComment;

    @Autowired
    private final TestClassTag testClassTag;

    @Autowired
    private final RoleServiceTest roleServiceTest;

    @Autowired
    private final TestUserFavoriteQuestion testUserFavoriteQuestion;

    @Autowired
    private final TestClassCommentAnswer testClassCommentAnswer;

    @Autowired
    private final TestClassCommentQuestion testClassCommentQuestion;

    private void init() throws Exception {

        // Создаем роли
        Role roleAdmin = Role.builder()
                .name("ADMIN")
                .build();
        testClassRole.persist(roleAdmin);

        Role roleUser = Role.builder()
                .name("USER")
                .build();
        testClassRole.persist(roleUser);

        // тестовые аккаунты: admin 1
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
                .role(testClassRole.getByKey(1L))
                .isEnabled(true)
                .build();
        testClassUser.persist(admin);

        // тестовые аккаунты: user 1
        User user1 = User.builder()
                .email("user1@user.ru")
                .password("user1")
                .fullName("Иван Иванович Иванов")
                .reputationCount(4)
                .city("Moscow")
                .linkSite("site.user1.ru")
                .linkGitHub("github.user1.ru")
                .linkVk("vk.user1.ru")
                .about("about user1")
                .role(testClassRole.getByKey(2L))
                .isEnabled(true)
                .build();
        testClassUser.persist(user1);

//         тестовые аккаунты: user 2
        User user2 = User.builder()
                .email("user2@user.ru")
                .isEnabled(true)
                .password("user2")
                .fullName("Петр Петрович Петров")
                .reputationCount(3)
                .city("SPB")
                .linkSite("site.user2.ru")
                .linkGitHub("github.user2.ru")
                .linkVk("vk.user2.ru")
                .about("about user2")
                .role(testClassRole.getByKey(2L))
                .build();
        testClassUser.persist(user2);

        // тестовые сущности: tag 1
        Tag tag1 = Tag.builder()
                .name("Main tag1")
                .description("Description tag1")
                .persistDateTime(LocalDateTime.now())
                .build();
        testClassTag.persist(tag1);

        // тестовые сущности: tag 2
        Tag tag2 = Tag.builder()
                .name("Main tag2")
                .description("Description tag2")
                .persistDateTime(LocalDateTime.now())
                .build();
        testClassTag.persist(tag2);

        // тестовые сущности: question 1
        Question question1 = Question.builder()
                .title("Question1 title")
                .viewCount(3)
                .description("Question1 description")
                .persistDateTime(LocalDateTime.now())
                .user(testClassUser.getByKey(2L))
                .countValuable(2)
                .build();

        // создание листа с тэгами для question1
        List<Tag> tagList1 = new ArrayList<>();
        tagList1.add(tag1);
        tagList1.add(tag2);
        question1.setTags(tagList1);

        // тестовые сущности: question 1
        Question question2 = Question.builder()
                .title("Question2 title")
                .viewCount(4)
                .description("Question2 description")
                .persistDateTime(LocalDateTime.now())
                .user(testClassUser.getByKey(3L))
                .countValuable(2)
                .build();

        List<Tag> tagList2 = new ArrayList<>();
        tagList2.add(tag2);
        question2.setTags(tagList2);

        // создание списка вопросов для тэга1
        List<Question> questionList1 = new ArrayList<>();
        questionList1.add(testClassQuestion.getByKey(1L));
        tag1.setQuestions(questionList1);
        testClassQuestion.persist(question1);

        // создание списка вопросов для тэга2
        List<Question> questionList2 = new ArrayList<>();
        questionList2.add(question1);
        questionList2.add(question2);
        tag2.setQuestions(questionList2);
        testClassQuestion.persist(question2);

        // создание ответов для question1
        Answer answer1_1 = Answer.builder()
//                .id(1L)
                .user(user2)
                .countValuable(2)
                .isHelpful(true)
                .question(question1)
                .dateAcceptTime(LocalDateTime.now())
                .persistDateTime(LocalDateTime.now())
                .htmlBody("Helpfull answer for question 1")
                .build();
        testClassAnswer.persist(answer1_1);

        Answer answer1_2 = Answer.builder()
                .user(user1)
                .countValuable(2)
                .question(question1)
                .dateAcceptTime(LocalDateTime.now())
                .persistDateTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpfull answer for question 1")
                .build();
        testClassAnswer.persist(answer1_2);

        Comment comment1 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 1 text")
                .user(user1)
                .build();

        CommentAnswer commentAnswer = CommentAnswer.builder()
                .comment(comment1)
                .answer(answer1_1)
                .build();
        testClassCommentAnswer.persist(commentAnswer);

        Comment comment2 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 2 text")
                .user(user1)
                .build();

        CommentAnswer commentAnswer2 = CommentAnswer.builder()
                .comment(comment2)
                .answer(answer1_1)
                .build();
        testClassCommentAnswer.persist(commentAnswer2);

        Comment comment3 = Comment.builder()
                .commentType(CommentType.QUESTION)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 3 text")
                .user(user2)
                .build();

        CommentQuestion commentQuestion1 = CommentQuestion.builder()
                .comment(comment3)
                .question(question1)
                .build();
        testClassCommentQuestion.persist(commentQuestion1);

        UserFavoriteQuestion userFavoriteQuestion = UserFavoriteQuestion.builder()
                .user(testClassUser.getByKey(2L))
                .persistDateTime(LocalDateTime.now())
                .question(testClassQuestion.getByKey(2L))
                .build();
        testUserFavoriteQuestion.persist(userFavoriteQuestion);
    }
}
