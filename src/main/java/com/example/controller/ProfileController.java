package com.example.controller;

import com.example.dto.CreatedProfileDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.HttpRequestUtil;
import com.example.util.JWTUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;
    @PostMapping("/adm")
    public ResponseEntity<CreatedProfileDTO>crete(@RequestBody CreatedProfileDTO dto ,
                                           HttpServletRequest request){
     Integer id=   HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN,ProfileRole.MODERATOR);

        return ResponseEntity.ok(service.crete(dto));
    }
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id")Integer id,
                                              HttpServletRequest request,
                                              @RequestHeader(value = "Authorization" )String jwt){
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
     /*   JwtDTO jwtDTO=JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        return ResponseEntity.ok(service.deleteById(id));
    }
    @PostMapping("/adm/update")
    public ResponseEntity<Boolean>update(@RequestBody ProfileDTO dto,
                                         HttpServletRequest request){
        HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(service.update(dto));
    }
    public  ResponseEntity<PageImpl<CreatedProfileDTO>> filter(@RequestParam(value = "page",defaultValue = "1" )Integer page,
                                                               @RequestParam(value = "size",defaultValue = "2") Integer size,
                                                               @RequestBody Filter filter){

        Pageable pageable= PageRequest.of(page-1,size, Sort.Direction.DESC,"createdDate");
        return ResponseEntity.ok(service.filter(filter,pageable));
    }

}
