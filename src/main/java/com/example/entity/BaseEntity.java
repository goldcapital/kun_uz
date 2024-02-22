package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "create_date")
    private LocalDateTime created_date= LocalDateTime.now();
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate=LocalDateTime.now();
    @Column(name = "visible")
    private  Boolean visible=true;
}