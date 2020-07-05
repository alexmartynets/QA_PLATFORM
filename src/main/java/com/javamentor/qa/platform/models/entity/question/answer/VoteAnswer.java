package com.javamentor.qa.platform.models.entity.question.answer;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes_on_answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteAnswer {

    @EmbeddedId
    private VoteAnswerPK voteAnswerPK;

    public VoteAnswer(User user, Answer answer, int vote) {
        this.vote = vote;
        this.voteAnswerPK = new VoteAnswerPK(user, answer);
    }

    private int vote;

    @PrePersist
    private void prePersistFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
        if (vote != 1 && vote != -1) {
            throw new RuntimeException("В сущности VoteAnswer допускается передача значения в поле vote только 1 или -1");
        }
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VoteAnswerPK implements Serializable {

        @ManyToOne
        private User user;

        @ManyToOne
        private Answer answer;

        @Field(store = Store.YES)
        @Column(name = "persist_date", updatable = false)
        @Type(type = "org.hibernate.type.LocalDateTimeType")
        private LocalDateTime localDateTime = LocalDateTime.now();

        public VoteAnswerPK(User user, Answer answer) {
            this.user = user;
            this.answer = answer;
        }
    }
}
