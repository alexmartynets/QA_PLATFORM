package com.javamentor.qa.platform.models.entity.user;

import com.javamentor.qa.platform.models.entity.Badges;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_badges")
public class UserBadges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean ready;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="badges_id")
    private Badges badges;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        UserBadges that = (UserBadges) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(ready, that.ready);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ready);
    }
}

