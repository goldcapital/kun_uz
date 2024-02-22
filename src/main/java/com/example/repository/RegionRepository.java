package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    @Query("update RegionEntity set order_number=?1 where id=?2")
    void update(String s, Integer id);
    @Query("from RegionEntity where language=?1")

    List<RegionEntity> getAll(String s);
}
