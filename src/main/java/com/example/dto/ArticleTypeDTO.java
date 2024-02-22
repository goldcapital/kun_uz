package com.example.dto;

import com.example.enums.LanguageEnums;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleTypeDTO {
  private Integer  id;
   private String order_number;
   private String language;
    private Boolean visible;
    private String articleType;
    private LocalDate created_date;
}
