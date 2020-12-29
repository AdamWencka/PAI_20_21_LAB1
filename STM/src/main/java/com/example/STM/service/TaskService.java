package com.example.STM.service;

import com.example.STM.model.Task;
import com.example.STM.model.User;
import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import com.example.STM.repository.TaskRepository;
import com.example.STM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAllByDateDesc();
    }
    public Task createTask(String title, String description, Type type, Status status, Integer accountId) {
        Optional<User> assignedUser = userRepository.findById(accountId);
        if (assignedUser.isPresent()) {
            User userFound = assignedUser.get();
            Task taskToSave = new Task(title, description, type, status, userFound);
            return taskRepository.save(taskToSave);
        }
        return null;
    }

    public List<Task> getTask(Optional<String> name, Optional<Status> status, Optional<Type> type){
        if (name.isPresent()){
            return taskRepository.findByTitle(name.get());
        }else if (status.isPresent()){
            return taskRepository.findByStatus(status.get());
        }else if (type.isPresent()){
            return taskRepository.findByType(type.get());
        }
        return null;
    }

    public String changeTaskStatus(Integer id, Status status){
        Optional<Task> taskStatusToChange = taskRepository.findById(id);
        if (taskStatusToChange.isPresent()){
            Task taskFound = taskStatusToChange.get();
            if(status!= taskFound.getStatus()){
                taskFound.setStatus(status);
                taskRepository.save(taskFound);
                return  "Changed status to: " + taskFound.getStatus();
            }else {
                return  "Task has already that status";
            }
        }
        return "Status not changed";
    }




    public String deleteTask(Integer id) {
        Optional<Task> taskToDelete = taskRepository.findById(id);
        if (taskToDelete.isPresent()) {
            Task taskFound = taskToDelete.get();
            String taskId = String.valueOf(taskFound.getTaskId());
            taskRepository.delete(taskFound);
            return "Deleted task id: " + taskId;
        }
        return "Task not found";
    }
}
