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

    // zad - a
    public User insertUser(User user){
     return userRepository.save(user);
    }
    //zad - b
    public List<User> selectAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByID(Integer id){
        return userRepository.findById(id);
    }
    public Optional findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // zad - c
    public User findByIdOrEmail(String k){
        Optional<User> user;
        if(k.contains("@")) user = userRepository.findByEmail(k);
        else user = userRepository.findById(Integer.valueOf(k));
        return user.get();
    }


    // zad - d
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
    // zad - e ( user ma CascadeType.ALL dzięki czemu wszystkie zadania użytkownika zostaną usunięte
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
