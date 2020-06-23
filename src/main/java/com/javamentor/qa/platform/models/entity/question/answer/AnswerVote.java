package com.javamentor.qa.platform.models.entity.question.answer;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes_on_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerVote {

    @EmbeddedId
    private VoteAnswerPK voteAnswerPK;
    private int vote;

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

        @CreationTimestamp
        @Column(name = "persist_date", updatable = false)
        @Type(type = "org.hibernate.type.LocalDateTimeType")
        private LocalDateTime persistDateTime;

        public VoteAnswerPK(User user, Answer answer) {
            this.user = user;
            this.answer = answer;
        }
    }

}
