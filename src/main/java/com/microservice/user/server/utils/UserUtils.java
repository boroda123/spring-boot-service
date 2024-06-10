package com.microservice.user.server.utils;


import com.microservice.user.server.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {

    private static List<User> users = new ArrayList<User>();

    public static List<User> getUsers() {

        if (users.size() == 0) {
            users.add(new User(0, "Jan", "First", "jan.first"));
            users.add(new User(1, "Feb", "Second", "feb.second"));
        }
        return users;
    }

}
