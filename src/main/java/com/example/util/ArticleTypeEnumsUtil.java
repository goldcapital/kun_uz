package com.example.util;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.enums.ArticleTypeEnums;
import com.example.enums.LanguageEnums;

public class ArticleTypeEnumsUtil {
    public static  ArticleTypeEntity enums(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();

        if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.DOLZARB).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.DOLZARB);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.ASOSIY).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.ASOSIY);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.FOTO).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.FOTO);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.INTERVIEW).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.INTERVIEW);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.YANGILIK).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.YANGILIK);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.BIZNES).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.BIZNES);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.SURUSHTURUV).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.SURUSHTURUV);
            return entity;

        } else if (dto.getArticleType().equals(String.valueOf(ArticleTypeEnums.VEDEO_YANGILIK).toLowerCase())) {
            entity.setArticleType(ArticleTypeEnums.VEDEO_YANGILIK);
            return entity;
        } else {
            entity.setArticleType(ArticleTypeEnums.MAQOLA);
        }
        return entity;
    }
    public static  ArticleTypeEntity enumsLanguage(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();

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

}
