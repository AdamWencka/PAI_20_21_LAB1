package com.example.STM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private boolean status = false;
    @Column(name = "registration_time")
    private LocalDateTime registrationDateTime = LocalDateTime.now();

    public User(String name, String lastName, String email, String password, boolean status, LocalDateTime registrationDateTime) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.registrationDateTime = registrationDateTime;
    }
}
