package com.javamentor.qa.platform.models.entity.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Column
    private String fullName;

    @Column(name = "persist_date", nullable = false, updatable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @CreationTimestamp
    private LocalDateTime persistDateTime;

    @Column(name = "is_enabled", nullable = false, columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isEnabled = true;

    @Column(name = "reputation_count")
    private Integer reputationCount;

    @Column
    private String city;

    @Column(name = "link_site")
    private String linkSite;

    @Column(name = "link_github")
    private String linkGitHub;

    @Column(name = "link_vk")
    private String linkVk;

    @Lob
    @Column
    private String about;

    @Lob
    @Column(name = "image")
    private Blob imageUser;

    @Column(name = "last_redaction_date", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @UpdateTimestamp
    private LocalDateTime lastUpdateDateTime;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(persistDateTime, user.persistDateTime) &&
                Objects.equals(isEnabled, user.isEnabled) &&
                Objects.equals(reputationCount, user.reputationCount) &&
                Objects.equals(city, user.city) &&
                Objects.equals(linkSite, user.linkSite) &&
                Objects.equals(linkGitHub, user.linkGitHub) &&
                Objects.equals(linkVk, user.linkVk) &&
                Objects.equals(about, user.about) &&
                Objects.equals(imageUser, user.imageUser) &&
                Objects.equals(lastUpdateDateTime, user.lastUpdateDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, fullName, persistDateTime, isEnabled, reputationCount, city, linkSite, linkGitHub, linkVk, about, imageUser, lastUpdateDateTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
