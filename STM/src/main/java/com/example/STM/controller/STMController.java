package com.example.STM.controller;

import com.example.STM.model.Task;
import com.example.STM.model.User;
import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import com.example.STM.service.TaskService;
import com.example.STM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class STMController {
    private UserService userService;
    private TaskService taskService;
    @Autowired
    public STMController(UserService userService, TaskService taskService){
        this.userService = userService;
        this.taskService = taskService;
    }
    @PostMapping("/users/userCreate")
    public User createUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password)
    {
        return userService.insertUser(new User(name,lastName,email,password));
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.selectAllUsers();
    }

    @GetMapping("/user/id={id}")
    public User findUserById(@PathVariable("id")Integer id){
        Optional userFound = userService.findByID(id);
        if(!userFound.isPresent()){
            return null;
        }
        return (User) userFound.get();
    }

    @GetMapping("/user/email={email}")
    public User findUserByEmail(@PathVariable("email") String email){
        Optional userFound = userService.findByEmail(email);
        if(!userFound.isPresent()){
            return null;
        }
        return (User) userFound.get();
    }

    @PutMapping("/user/status/id={id}")
    public boolean activateUser(@PathVariable("id") Integer id){
        return userService.activateUser(id);
    }
    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam("id")Integer id){
        return userService.deleteUserByID(id);
    }
    @PostMapping("/task/taskCreate")
    public Task createTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Type type,
            @RequestParam("status")Status status,
            @RequestParam("user") Integer userId
            ){
            return taskService.createTask(title,description,type,status,userId);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    
    @GetMapping("/task")
    public List<Task> getTask(
            @RequestParam("name") Optional<String> name,
            @RequestParam("status") Optional<Status> status,
            @RequestParam("type") Optional<Type> type
    ){
        return taskService.getTask(name,status,type);
    }

    @PutMapping("/task/status")
    public  String changeTaskStatus(
            @RequestParam("id") Integer id,
            @RequestParam("status") Status status){
        return taskService.changeTaskStatus(id,status);
    }


    @DeleteMapping("/task/delete")
    public String deleteTask(@RequestParam("id") Integer id){
        return taskService.deleteTask(id);
    }

}
