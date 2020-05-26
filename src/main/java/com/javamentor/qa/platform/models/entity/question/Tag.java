package com.javamentor.qa.platform.models.entity.question;

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
@Table(name = "tag")
@Indexed
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    @Field
    private String name;

    @Lob
    @Column
    @Field
    private String description;

    @CreationTimestamp
    @Column(name = "persist_date", updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Field
    @DateBridge(resolution = Resolution.DAY)
    private LocalDateTime persistDateTime;

    @IndexedEmbedded
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Question> questions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name) &&
                Objects.equals(description, tag.description) &&
                Objects.equals(persistDateTime, tag.persistDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, persistDateTime);
    }
}
