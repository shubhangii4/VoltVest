package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private UserType userType;
    
    public enum UserType {
        NEW_USER,
        EXISTING_USER
    }
} 