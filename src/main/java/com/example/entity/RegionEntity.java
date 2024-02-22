package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "region")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionEntity extends BaseRegionAndArticleEntity{
}
