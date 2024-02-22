package com.example.entity;

import com.example.enums.ArticleTypeEnums;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article_type")
public class ArticleTypeEntity extends BaseRegionAndArticleEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "article_type")
    private ArticleTypeEnums articleType;
}
