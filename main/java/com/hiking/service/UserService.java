package com.hiking.service;

import com.hiking.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getUsers();

    User getUser(int id);

    int getUserIdByName(String username);

    int getNumberOfDoneHikes(int user_id);

    int getNumberOfHikesTodo(int user_id);

    double getNbrOfKmsWalked(int user_id);
}
