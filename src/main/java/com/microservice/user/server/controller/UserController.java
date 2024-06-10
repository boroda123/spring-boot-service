package com.microservice.user.server.controller;

import com.microservice.user.server.dto.UserDataDto;
import com.microservice.user.server.dto.ErrorDto;
import com.microservice.user.server.dto.UserDto;
import com.microservice.user.server.model.User;
import com.microservice.user.server.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User Controller", description = "Manage Users: list, find, create, delete.")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET, POST, PUT, PATCH, DELETE

    @GetMapping
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get a list of all Users")
    public UserDataDto getUserList() {
        List<User> userList = (List<User>) userRepository.findAll();
        UserDataDto data = new UserDataDto(null, userList);
        return data;
    }

    @GetMapping(path = "/{userId}")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get a single User by ID")
    @Parameter(name="userId", required = true, description="URL path parameter: User ID (required)")
    public UserDataDto getUserDetails(@PathVariable(value = "userId") int userId) {
        User user = verifyUser(userId);
        List<User> list = new ArrayList<>();
        list.add(user);
        UserDataDto data = new UserDataDto(null, list);
        return data;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    @Operation(summary = "Find User by its ID")
    @Parameter(name="userId", required = true, description="URL query parameter: User ID (required)")
    public UserDataDto getUserDetailsWithParam(@RequestParam(name = "userId") int userId) {
        User user = verifyUser(userId);
        List<User> list = new ArrayList<>();
        list.add(user);
        UserDataDto data = new UserDataDto(null, list);
        return data;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new User")
    @Parameter(name="UserDto", required = true, description="New User info: firstName, lastName, login")
    public void createUser( @RequestBody @Validated UserDto userDto) {
        User user = verifyUserDto(userDto);
        userRepository.save(user);
    }

    @DeleteMapping(path = "/{userId}")
    @CrossOrigin(origins = "*")
    @Operation(summary = "Delete a User by ID")
    @Parameter(name="userId", required = false, description="URL path parameter: User ID (required)")
    public void removeUser(@PathVariable(value = "userId") int userId) {
        User user = verifyUser(userId);
        userRepository.delete(user);
    }

    private User verifyUser(int userId) throws NoSuchElementException {
        if (userId < 0) {
            throw new NoSuchElementException("Invalid user ID: " + userId);
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NoSuchElementException("No User exists for this ID: " + userId);
        }
        return userOpt.get();
    }

    private User verifyUserDto(UserDto dto) throws NoSuchElementException {
        if (dto == null) {
            throw new NoSuchElementException("Input is empty");
        }
        User user =  userRepository.findByLogin(dto.getLogin());
        if (user != null) {
            throw new NoSuchElementException("User already exists. Login: "+dto.getLogin());
        }
        user = new User(null, dto.getFirstName(), dto.getLastName(), dto.getLogin());
        return user;
    }


    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public UserDataDto return400(NoSuchElementException ex) {
        return new UserDataDto(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()), null);
    }

}
