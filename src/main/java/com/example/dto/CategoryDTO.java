package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CategoryDTO {
    private Integer  id;
    private String order_number;
    private String language;
    private Boolean visible;
    private LocalDate created_date;
    private String category;
}
