package com.example.STM.repository;

import com.example.STM.model.Task;
import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM tasks ORDER BY date_added DESC")
    List<Task> findAllByDateDesc();

    List<Task> findByTitle(String title);

    List<Task> findByStatus(Status status);

    List<Task> findByType(Type type);



}
