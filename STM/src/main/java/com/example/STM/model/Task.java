package com.example.STM.model;

import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();
    private Type type;
    private Status status;
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private User author;

    public Task(String title, String description, LocalDateTime dateAdded, Type type, Status status,User author) {
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.type = type;
        this.status = status;
        this.author =author;
    }
}
