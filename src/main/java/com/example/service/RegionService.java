package com.example.service;

import com.example.dto.RegionDto;
import com.example.entity.RegionEntity;
import com.example.enums.LanguageEnums;
import com.example.exp.AppBadException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository repository;
    public RegionDto crete(RegionDto dto) {
        RegionEntity entity=new RegionEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setLanguage(enumsLanguage(dto).getLanguage());
        repository.save(entity);
        return toDTO(entity);

    }
    public RegionEntity enumsLanguage(RegionDto dto) {
        RegionEntity entity=new RegionEntity();

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

    private RegionDto toDTO(RegionEntity entity) {
        RegionDto dto=new  RegionDto();
        dto.setOrder_number(entity.getOrder_number());
        dto.setId(entity.getId());
        dto.setLanguage(String.valueOf(entity.getLanguage()));
        return dto;
    }

    public Boolean update(Integer id, RegionDto dto) {
        Optional<RegionEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("ID TOPILMADI");
        }
        repository.update(String.valueOf(enumsLanguage(dto)), id);
        return null;
    }

    public Boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public List<RegionDto> getRegionLanguage(String language) {

        RegionDto dto=new  RegionDto();
        List<RegionDto>dtoList=new LinkedList<>();

        Iterable<RegionEntity>list=repository.findAll();
        for (RegionEntity entity:list){
            dto.setId(entity.getId());
          // dto.setLaрnguage( String.valueOf(enumsLanguage(language)));
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }
}
