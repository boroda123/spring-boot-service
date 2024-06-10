package com.microservice.user.server.dto;

import com.microservice.user.server.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDataDto {

    private ErrorDto error;

    private Iterable<User> data;

    public UserDataDto(ErrorDto error, Iterable<User> data) {
        this.error = error;
        this.data = data;
    }
}
