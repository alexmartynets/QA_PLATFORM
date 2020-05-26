package com.javamentor.qa.platform.models.entity.question;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;

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
@Indexed
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Field
    private String title;

    @NotNull
    @Column(name = "view_count")
    @Field
    private Integer viewCount = 0;

    @Lob
    @NotNull
    @Column
    @Field
    private String description;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Field
    @DateBridge(resolution = Resolution.DAY)
    private LocalDateTime persistDateTime;

    @NotNull
    @Column(name = "count_valuable")
    @Field
    private Integer countValuable = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_has_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(title, question.title) &&
                Objects.equals(viewCount, question.viewCount) &&
                Objects.equals(description, question.description) &&
                Objects.equals(persistDateTime, question.persistDateTime) &&
                Objects.equals(countValuable, question.countValuable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, viewCount, description, persistDateTime, countValuable);
    }
}
