package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false)
    private  String surname;
    @Column(unique = true,nullable = false)
    private  String email;
    private  String phone;
    @Column(nullable = false)
    private  String password;
    private  Integer photo_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;
}
