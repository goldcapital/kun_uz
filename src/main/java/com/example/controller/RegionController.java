package com.example.controller;


import com.example.dto.JwtDTO;
import com.example.dto.RegionDto;
import com.example.enums.ProfileRole;
import com.example.service.RegionService;
import com.example.util.HttpRequestUtil;
import com.example.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService service;
    @PostMapping("")
    public ResponseEntity<RegionDto> crete(@RequestBody RegionDto dto,
                                         HttpServletRequest request){
      Integer id= HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN,ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.crete(dto));
    }
    @PostMapping("/update")
    public ResponseEntity<Boolean>update(@PathVariable("id") Integer id,
                                         @RequestBody RegionDto dto,
                                         @RequestHeader(value = "Authorization") String jwt){
        JwtDTO jwtDTO = JWTUtil.decode(jwt);

        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.update(id,dto));
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.deleteById(id));
    }
  /*  @GetMapping("/pagination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                               @RequestParam(value = "size",defaultValue = "10") Integer size){
        return ResponseEntity.ok(service.pagination(page, size));
    }*/
    @GetMapping("/getByLanguage")
    public ResponseEntity<List<RegionDto>>getByLanguage(@PathVariable("name")
                                                             String name){
        return ResponseEntity.ok(service.getRegionLanguage(name));
    }
}

