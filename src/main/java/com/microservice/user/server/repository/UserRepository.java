package com.microservice.user.server.repository;

import com.microservice.user.server.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//@RestResource(exported = false)
@Tag(name = "User Repository", description = "Manage Users.")
public interface UserRepository extends CrudRepository<User, Integer> {

    @Operation(summary = "Find a User by its full Login")
    User findByLogin(String login);

    @Operation(summary = "Find Users by the partial Login, using a pre-defined SQL Query")
    @Query("select u from User u where u.login like %?1% order by u.userId asc")
    List<User> findAllByLoginQuery(String login);

}
