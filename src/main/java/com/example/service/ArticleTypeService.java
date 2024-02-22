package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.exp.AppBadException;
import com.example.repository.ArticleTypeRepository;
import com.example.util.ArticleTypeEnumsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository repository;

    public ArticleTypeDTO crete(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setLanguage(ArticleTypeEnumsUtil.enumsLanguage(dto).getLanguage());
        entity.setArticleType(ArticleTypeEnumsUtil.enums(dto).getArticleType());
        repository.save(entity);
        return toDTO(entity);
    }

    public Boolean update(Integer id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("ID TOPILMADI");
        }
        repository.update(String.valueOf(ArticleTypeEnumsUtil.enumsLanguage(dto)), id);
        return null;
    }

    public ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setOrder_number(entity.getOrder_number());
        dto.setId(entity.getId());
        dto.setLanguage(String.valueOf(entity.getLanguage()));
        return dto;
    }

    public Boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public PageImpl<ArticleTypeDTO> pagination(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page - 1, size,
                Sort.Direction.DESC, "createdDate");

        Page<ArticleTypeEntity> articleTypePage = repository.findAll(paging);

        List<ArticleTypeEntity> entityList = articleTypePage.getContent();
        Long totalElements = articleTypePage.getTotalElements();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : entityList) {
            dtoList.add(toDTO(entity));

        }
        return new PageImpl<>(dtoList, paging, totalElements);

    }
    public List<ArticleTypeDTO> getLanguage(String language){
        ArticleTypeDTO dto=new ArticleTypeDTO();
        List<ArticleTypeDTO>dtoList=new LinkedList<>();
        dto.setLanguage(language);
        List<ArticleTypeEntity>list=repository.getAll(String.valueOf(ArticleTypeEnumsUtil.enumsLanguage(dto)));
        for (ArticleTypeEntity entity:list){
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }
}
