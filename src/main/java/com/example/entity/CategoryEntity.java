package com.example.entity;

import com.example.entity.BaseRegionAndArticleEntity;
import com.example.enums.CategoryEnums;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseRegionAndArticleEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryEnums category;



}
