package com.javamentor.qa.platform.models.entity.question.answer;

import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "answer")
@SQLDelete(sql ="UPDATE answer SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

    @Column(name = "update_date")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime updateDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @NotNull
    @Column
    private String htmlBody;

    @NotNull
    @Column(name = "is_helpful")
    private Boolean isHelpful;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "date_accept_time")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime dateAcceptTime;

    @NotNull
    @Column(name = "count_valuable")
    private Integer countValuable = 0;

    @PrePersist
    private void prePersistFunction() {
        checkConstraints();
    }

    @PreUpdate
    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
        if (this.htmlBody.isEmpty()) {
            throw new RuntimeException("Поле htmlBody должно быть заполнено");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(persistDateTime, answer.persistDateTime) &&
                Objects.equals(updateDateTime, answer.updateDateTime) &&
                Objects.equals(question, answer.question) &&
                Objects.equals(user, answer.user) &&
                Objects.equals(htmlBody, answer.htmlBody) &&
                Objects.equals(isHelpful, answer.isHelpful) &&
                Objects.equals(isDeleted, answer.isDeleted) &&
                Objects.equals(dateAcceptTime, answer.dateAcceptTime) &&
                Objects.equals(countValuable, answer.countValuable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persistDateTime, updateDateTime, question, user, htmlBody, isHelpful, isDeleted, dateAcceptTime, countValuable);
    }
}
