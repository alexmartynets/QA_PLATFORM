package com.javamentor.qa.platform.models.entity.question.answer;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

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
    private Boolean isHelpful = false;

    @Column(name = "date_accept_time")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime dateAcceptTime;

    @NotNull
    @Column(name = "count_valuable")
    private Integer countValuable = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(persistDateTime, answer.persistDateTime) &&
                Objects.equals(htmlBody, answer.htmlBody) &&
                Objects.equals(isHelpful, answer.isHelpful) &&
                Objects.equals(dateAcceptTime, answer.dateAcceptTime) &&
                Objects.equals(countValuable, answer.countValuable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persistDateTime, htmlBody, isHelpful, dateAcceptTime, countValuable);
    }
}
