package com.example.STM.service;

import com.example.STM.model.User;

import com.example.STM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User insertUser(User user){
     return userRepository.save(user);
    }
    public List<User> selectAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByID(Integer id){
        return userRepository.findById(id);
    }
    public Optional findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public boolean activateUser(Integer id){
        Optional<User> userToActivate = userRepository.findById(id);
        boolean userStatus= false;
        if(userToActivate.isPresent()){
            User userFound = userToActivate.get();
            userStatus = userFound.isStatus();
            userFound.setStatus(!userStatus);
            userRepository.save(userFound);
            return !userStatus;
        }
        return userStatus;
    }
    public String deleteUserByID(Integer id){
        Optional<User> userToDelete = userRepository.findById(id);
        if(userToDelete.isPresent()){
            User userFound = userToDelete.get();
            String userId= String.valueOf(userFound.getUserId());
            userRepository.delete(userFound);
            return "Deleted: " + userId;
        }
        return "User not found!";
    }




}
