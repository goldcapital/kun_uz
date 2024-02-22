package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.CreatedProfileDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    public CreatedProfileDTO crete(CreatedProfileDTO dto) {
if(dto.getEmail()!=null) {
    if (dto.getEmail().length() <= 8) {
        throw new AppBadException("EMAIL MUST BE LONGER THAN 8");
    }
    return checkCrete(dto);

}else if(dto.getPhone()!=null){
    if (dto.getPhone().length()>13) {
        throw new AppBadException("PHONE MUST BE LONGER THAN 13");
    }
    return checkCrete(dto);
}
throw new AppBadException("PHONE or EMAIL SELECT");

    }

    public Boolean deleteById(Integer id) {
     repository.deleteById(id);
        return true;
    }
    private CreatedProfileDTO checkCrete(CreatedProfileDTO dto){
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new AppBadException("Profile password required");
        }
        ProfileEntity entity = new ProfileEntity();
        if (dto.getStatus() == null) {
            entity.setStatus(ProfileStatus.ACTIVE);
        } else {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getRole() == null) {
            entity.setRole(ProfileRole.USER);
        } else {
            entity.setRole(dto.getRole());
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        return toDTo(entity);

    }
    public  CreatedProfileDTO toDTo(ProfileEntity entity) {
        CreatedProfileDTO dto=new CreatedProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        return dto;

    }

    public Boolean update(ProfileDTO dto) {

        ProfileEntity entity=new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setStatus(dto.getStatus());
        entity.setPassword(dto.getPassword());
        entity.setSurname(dto.getSurname());
        entity.setRole(dto.getRole());
        entity.setName(dto.getName());

        repository.save(entity);
        return null;
    }
    private ProfileEntity toEntity(ProfileDTO dto){
        ProfileEntity entity=new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setSurname(dto.getSurname());
        entity.setName(dto.getName());
        return entity;
    }

    public PageImpl<CreatedProfileDTO >filter(Filter filter, Pageable pageable) {
        return null;
    }
}
