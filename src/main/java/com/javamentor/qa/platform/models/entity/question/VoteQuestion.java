package com.javamentor.qa.platform.models.entity.question;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes_on_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteQuestion {

    @EmbeddedId
    private VoteQuestionPK voteQuestionPK;

    public VoteQuestion(User user, Question question, int vote) {
        this.vote = vote;
        this.voteQuestionPK = new VoteQuestionPK(user, question);
    }

    private int vote;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VoteQuestionPK implements Serializable {

        @ManyToOne
        private User user;

        @ManyToOne
        private Question question;

        @CreationTimestamp
        @Field(store = Store.YES)
        @Column(name = "persist_date", updatable = false)
        @Type(type = "org.hibernate.type.LocalDateTimeType")
        private LocalDateTime localDateTime = LocalDateTime.now();

        public VoteQuestionPK(User user, Question question) {
            this.user = user;
            this.question = question;
        }
    }
}
