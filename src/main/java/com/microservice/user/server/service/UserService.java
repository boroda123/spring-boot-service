package com.microservice.user.server.service;

import com.microservice.user.server.dto.UserDataDto;

public interface UserService {
    public UserDataDto findAllUsers();
}
