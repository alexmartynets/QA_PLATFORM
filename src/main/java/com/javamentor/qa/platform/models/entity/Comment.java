package com.javamentor.qa.platform.models.entity;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    @Column
    private String text;

    @Enumerated
    @NotNull
    @Column(columnDefinition = "smallint")
    private CommentType commentType;

    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @CreationTimestamp
    private LocalDateTime persistDateTime;

    @Column(name = "last_redaction_date")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdateDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public Comment(CommentType commentType) {
        this.commentType = commentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(persistDateTime, comment.persistDateTime) &&
                Objects.equals(lastUpdateDateTime, comment.lastUpdateDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, persistDateTime, lastUpdateDateTime);
    }
}
