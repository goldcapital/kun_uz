package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity,Integer> {
    @Query("update ArticleTypeEntity set order_number=?1 where id=?2")
    void update(String number,Integer id);

    Page<ArticleTypeEntity> findAll(Pageable paging);
@Query("from ArticleTypeEntity where language=?1")
    List<ArticleTypeEntity> getAll(String language);
}
