package com.microservice.user.server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@Setter
@ToString(includeFieldNames = true)
@Comment("User data")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Column(name = "FIRSTNAME")
    @Comment("User first name")
    private String firstName;

    @NotNull
    @Column(name = "LASTNAME")
    @Comment("User last name")
    private String lastName;

    @NotNull
    @Column(name = "LOGIN")
    @Comment("User login")
    private String login;

    public User() {}

    public User(Integer userId, String firstName, String lastName, String login) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
        //return Objects.hash(id, firstName,lastName, login;
    }

}
