package com.example.entity;
import com.example.enums.LanguageEnums;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
public class BaseRegionAndArticleEntity extends BaseEntity {
    @Column(nullable = false)
    private String order_number;
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private LanguageEnums language;
}
