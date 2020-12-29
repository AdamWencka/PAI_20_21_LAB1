package com.example.STM.model;

import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private LocalDateTime dateAdded ;
    private Type type;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_assigned",nullable = false)
    @JsonIgnoreProperties({"tasks"})
    private User author;

    public Task(String title, String description,  Type type, Status status) {
        this.title = title;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.type = type;
        this.status = status;
    }
    public Task(String title, String description, Type type, Status status,User author) {
        this.title = title;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.type = type;
        this.status = status;
        this.author =author;
    }
}
