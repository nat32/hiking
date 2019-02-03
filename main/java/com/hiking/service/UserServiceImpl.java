package com.hiking.service;

import com.hiking.model.User;
import com.hiking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        return userRepository.createUser(user);
    }

    @Override
    public List<User> getUsers() {

        return userRepository.getUsers();
    }


    @Override
    public User getUser(int id){

        return userRepository.getUser(id);
    }

    @Override
    public int getUserIdByName(String username){

        return userRepository.getUserIdByName(username);
    }

    @Override
    public int getNumberOfDoneHikes(int user_id){

        return userRepository.getNumberOfDoneHikes(user_id);
    }

    @Override
    public int getNumberOfHikesTodo(int user_id){

        return userRepository.getNumberOfHikesTodo(user_id);
    }

    @Override
    public double getNbrOfKmsWalked(int user_id){

        return userRepository.getNbrOfKmsWalked(user_id);
    }
}
