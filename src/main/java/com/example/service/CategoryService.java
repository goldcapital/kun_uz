package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDto;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.CategoryEnums;
import com.example.enums.LanguageEnums;
import com.example.exp.AppBadException;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public CategoryDTO crete(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setLanguage(enumsLanguage(dto).getLanguage());
        entity.setCategory(enumsCategory(dto).getCategory());
        repository.save(entity);
        return toDTO(entity);

    }

    private CategoryEntity enumsCategory(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();

        if (dto.getCategory().equals(String.valueOf(CategoryEnums.AUDIO).toLowerCase())){
            entity.setCategory(CategoryEnums.AUDIO);
            return entity;
        }
        if(dto.getCategory().equals(String.valueOf(CategoryEnums.JAXON).toLowerCase())){
           entity.setCategory(CategoryEnums.JAXON);
            return entity;
        }
        if(dto.getCategory().equals(String.valueOf(CategoryEnums.IQTISODEYOT).toLowerCase())){
            entity.setCategory(CategoryEnums.IQTISODEYOT);
            return entity;
        }
        entity.setCategory(CategoryEnums.UZBEKISTON);
        return entity;
    }

    private CategoryEntity enumsLanguage(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();

        if (dto.getLanguage().startsWith(String.valueOf(LanguageEnums.En).toLowerCase())) {
            entity.setLanguage(LanguageEnums.En);
            return entity;
        }
        if (dto.getLanguage().startsWith(String.valueOf(LanguageEnums.RUS).toLowerCase())) {
            entity.setLanguage(LanguageEnums.RUS);
            return entity;
        }
        entity.setLanguage(LanguageEnums.UZ);
        return entity;
    }

    private CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        dto.setId(entity.getId());
        dto.setLanguage(String.valueOf(entity.getLanguage()));
        return dto;
    }

    public Boolean update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("ID TOPILMADI");
        }
     //   repository.update(String.valueOf(enumsLanguage(dto)), id);
        return null;
    }

    public Boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public List<CategoryDTO> getRegionLanguage(String language) {


        List<CategoryDTO> dtoList = new LinkedList<>();

        Iterable<CategoryEntity> list = repository.findAll();
        for (CategoryEntity entity : list) {
            CategoryDTO dto = new CategoryDTO();
            dto.setLanguage(language);
            dto.setLanguage(language);
            entity.setLanguage(enumsLanguage(dto).getLanguage());
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }
}