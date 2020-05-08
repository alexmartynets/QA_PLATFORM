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
import com.javamentor.qa.platform.service.impl.model.*;
import com.javamentor.qa.platform.service.impl.model.Comment.CommentAnswerServiceImpl;
import com.javamentor.qa.platform.service.impl.model.Comment.CommentQuestionServiceImpl;
import com.javamentor.qa.platform.service.impl.model.Comment.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataEntityService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private CommentAnswerServiceImpl commentAnswerServiceImpl;

    @Autowired
    private CommentQuestionServiceImpl commentQuestionServiceImpl;

    @Autowired
    private TagServiceImpl tagServiceImpl;

    @Autowired
    private UserFavoriteQuestionServiceImpl userFavoriteQuestionServiceImpl;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private AnswerServiceImpl answerServiceImpl;

    @Autowired
    private RelatedTagServiceImpl relatedTagServiceImpl;

    public void createEntity() {
        creatRoleEntity();
        creatUserEntity();
        creatTagEntity();
        creatQuestionEntity();
        creatAnswerEntity();
        creatComment();
        creatUserFavoriteQuestion();
    }

    private void creatRoleEntity() {
        Role roleAdmin = Role.builder()
                .name("ADMIN")
                .build();
        roleServiceImpl.persist(roleAdmin);

        Role roleUser = Role.builder()
                .name("USER")
                .build();
        roleServiceImpl.persist(roleUser);
    }

    private void creatUserEntity() {
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
                .role(roleServiceImpl.getByKey(1L))
                .isEnabled(true)
                .build();
        userServiceImpl.persist(admin);

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
                .role(roleServiceImpl.getByKey(2L))
                .isEnabled(true)
                .build();
        userServiceImpl.persist(user1);

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
                .role(roleServiceImpl.getByKey(2L))
                .build();
        userServiceImpl.persist(user2);
    }

    private void creatTagEntity() {
        Tag tag1 = Tag.builder()
                .name("Main tag1")
                .description("Description tag1")
                .persistDateTime(LocalDateTime.now())
                .build();
        tagServiceImpl.persist(tag1);

        Tag tag2 = Tag.builder()
                .name("Child tag1")
                .description("Description tag2")
                .persistDateTime(LocalDateTime.now())
                .build();
        tagServiceImpl.persist(tag2);

        RelatedTag relatedTag = RelatedTag.builder()
                .mainTag(tag1)
                .childTag(tag2)
                .build();
        relatedTagServiceImpl.persist(relatedTag);
    }

    private void creatQuestionEntity() {
        Question question1 = Question.builder()
                .title("Question1 title")
                .viewCount(3)
                .description("Question1 description")
                .persistDateTime(LocalDateTime.now())
                .user(userServiceImpl.getByKey(2L))
                .countValuable(2)
                .build();

        List<Tag> tagList1 = new ArrayList<>();
        tagList1.add(tagServiceImpl.getByKey(1L));
        tagList1.add(tagServiceImpl.getByKey(2L));
        question1.setTags(tagList1);

        Question question2 = Question.builder()
                .title("Question2 title")
                .viewCount(4)
                .description("Question2 description")
                .persistDateTime(LocalDateTime.now())
                .user(userServiceImpl.getByKey(3L))
                .countValuable(2)
                .build();

        List<Tag> tagList2 = new ArrayList<>();
        tagList2.add(tagServiceImpl.getByKey(2L));
        question2.setTags(tagList2);

        List<Question> questionList1 = new ArrayList<>();
        questionList1.add(questionServiceImpl.getByKey(1L));
        tagServiceImpl.getByKey(1L).setQuestions(questionList1);
        questionServiceImpl.persist(question1);

        List<Question> questionList2 = new ArrayList<>();
        questionList2.add(question1);
        questionList2.add(question2);
        tagServiceImpl.getByKey(2L).setQuestions(questionList2);
        questionServiceImpl.persist(question2);
    }

    private void creatAnswerEntity() {
        Answer answer1_1 = Answer.builder()
                .user(userServiceImpl.getByKey(3L))
                .countValuable(2)
                .isHelpful(true)
                .question(questionServiceImpl.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .persistDateTime(LocalDateTime.now())
                .htmlBody("Helpfull answer for question 1")
                .build();
        answerServiceImpl.persist(answer1_1);

        Answer answer1_2 = Answer.builder()
                .user(userServiceImpl.getByKey(2L))
                .countValuable(2)
                .question(questionServiceImpl.getByKey(1L))
                .dateAcceptTime(LocalDateTime.now())
                .persistDateTime(LocalDateTime.now())
                .isHelpful(false)
                .htmlBody("Don't helpfull answer for question 1")
                .build();
        answerServiceImpl.persist(answer1_2);
    }

    private void creatComment() {
        Comment comment1 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 1 text")
                .user(userServiceImpl.getByKey(2L))
                .build();

        CommentAnswer commentAnswer = CommentAnswer.builder()
                .comment(comment1)
                .answer(answerServiceImpl.getByKey(1L))
                .build();
        commentAnswerServiceImpl.persist(commentAnswer);

        Comment comment2 = Comment.builder()
                .commentType(CommentType.ANSWER)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 2 text")
                .user(userServiceImpl.getByKey(2L))
                .build();

        CommentAnswer commentAnswer2 = CommentAnswer.builder()
                .comment(comment2)
                .answer(answerServiceImpl.getByKey(1L))
                .build();
        commentAnswerServiceImpl.persist(commentAnswer2);

        Comment comment3 = Comment.builder()
                .commentType(CommentType.QUESTION)
                .persistDateTime(LocalDateTime.now())
                .lastUpdateDateTime(LocalDateTime.now())
                .text("Comment 3 text")
                .user(userServiceImpl.getByKey(3L))
                .build();

        CommentQuestion commentQuestion1 = CommentQuestion.builder()
                .comment(comment3)
                .question(questionServiceImpl.getByKey(1L))
                .build();
        commentQuestionServiceImpl.persist(commentQuestion1);
    }

    private void creatUserFavoriteQuestion() {
        UserFavoriteQuestion userFavoriteQuestion = UserFavoriteQuestion.builder()
                .user(userServiceImpl.getByKey(2L))
                .persistDateTime(LocalDateTime.now())
                .question(questionServiceImpl.getByKey(2L))
                .build();
        userFavoriteQuestionServiceImpl.persist(userFavoriteQuestion);
    }
}
