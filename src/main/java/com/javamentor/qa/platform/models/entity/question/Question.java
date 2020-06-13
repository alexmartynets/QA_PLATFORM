package com.javamentor.qa.platform.models.entity.question;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

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
@Where(clause = "is_deleted = false")
@Indexed
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Field(store = Store.YES)
    private String title;

    @NotNull
    @Column(name = "view_count")
    @Field(store = Store.YES)
    private Integer viewCount = 0;

    @Lob
    @NotNull
    @Column
    @Field(store = Store.YES)
    private String description;

    @CreationTimestamp
    @Field(store = Store.YES)
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime persistDateTime;

    @NotNull
    @Field(store = Store.YES)
    @Column(name = "count_valuable")
    private Integer countValuable = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @IndexedEmbedded(includePaths = {"fullName", "reputationCount"})
    private User user;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_has_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @Column(name = "last_redaction_date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdateDateTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
            throw new RuntimeException("Экземпляр Question должен иметь в поле tags хотя бы один элемент");
        }
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
    }

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
                Objects.equals(lastUpdateDateTime, question.lastUpdateDateTime) &&
                Objects.equals(isDeleted, question.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, viewCount, description, persistDateTime, countValuable, user, tags, lastUpdateDateTime, isDeleted);
    }
}
