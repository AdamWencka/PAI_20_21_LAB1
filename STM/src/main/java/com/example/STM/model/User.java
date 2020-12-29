package com.example.STM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private boolean status ;
    @Column(name = "registration_time")
    private LocalDateTime registrationDateTime;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"author"})
    private List<Task> tasks;


    public User(String name, String lastName, String email, String password  ) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = false;
        this.registrationDateTime = LocalDateTime.now();
    }

}
