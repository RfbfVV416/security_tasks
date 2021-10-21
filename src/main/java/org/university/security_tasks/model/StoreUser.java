package org.university.security_tasks.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.university.security_tasks.annotations.OnCreate;
import org.university.security_tasks.annotations.OnUpdate;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.persistence.Enumerated;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="users", schema = "public",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "email"})},
        indexes = { @Index(columnList = "email")})
public class StoreUser {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name="USER_SEQ", sequenceName = "USERS_SEQUENCE_GENERATOR")
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated
    @NotNull
    private UserRolesProvider role;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreUser storeUser = (StoreUser) o;
        return id.equals(storeUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
