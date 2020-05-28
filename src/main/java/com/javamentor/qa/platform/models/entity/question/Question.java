package com.javamentor.qa.platform.models.entity.question;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String title;

    @NotNull
    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Lob
    @NotNull
    @Column
    private String description;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

    @NotNull
    @Column(name = "count_valuable")
    private Integer countValuable = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_has_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @NotNull
    @Column(name = "question_hide")
    private Boolean isQuestionHide;

    @PrePersist
    private void prePersistFunction() {
        checkConstraints();
    }

    @PreUpdate
    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
        if (this.tags == null || this.tags.isEmpty()) {
            throw new RuntimeException("Экземпляр Question должен иметь поле tags хотя бы один элемент");
            //todo написать своё исключение, и на isEmpty
        }
    }

    //todo добавить update data time

    //todo подумать над временем helpful

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(title, question.title) &&
                Objects.equals(viewCount, question.viewCount) &&
                Objects.equals(description, question.description) &&
                Objects.equals(persistDateTime, question.persistDateTime) &&
                Objects.equals(countValuable, question.countValuable) &&
                Objects.equals(user, question.user) &&
                Objects.equals(tags, question.tags) &&
                Objects.equals(isQuestionHide, question.isQuestionHide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, viewCount, description, persistDateTime, countValuable, user, tags, isQuestionHide);
    }
}
