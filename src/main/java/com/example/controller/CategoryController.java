package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.JwtDTO;
import com.example.dto.RegionDto;
import com.example.enums.ProfileRole;
import com.example.service.CategoryService;
import com.example.service.RegionService;
import com.example.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService service;
        @PostMapping("/adm")
        public ResponseEntity<CategoryDTO> crete(@RequestBody CategoryDTO dto,
                                               HttpServletRequest request,
                                               @RequestHeader(value = "Authorization") String jwt){
           /* JwtDTO jwtDTO= JWTUtil.decode(jwt);

            if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }*/
            Integer id = (Integer) request.getAttribute("id");
            ProfileRole role = (ProfileRole) request.getAttribute("role");

            if (!role.equals(ProfileRole.ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            return ResponseEntity.ok(service.crete(dto));
        }
        @PostMapping("/adm/update")
        public ResponseEntity<Boolean>update(@PathVariable("id") Integer id,
                                             HttpServletRequest request,
                                             @RequestBody CategoryDTO dto,
                                             @RequestHeader(value = "Authorization") String jwt){
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.ok(service.update(id,dto));
        }
        @DeleteMapping("/adm/delete")
        public  ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                               @RequestHeader(value = "Authorization") String jwt){
            JwtDTO jwtDTO=JWTUtil.decode(jwt);
            if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
              return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.ok(service.deleteById(id));
        }
        /*  @GetMapping("/pagination")
          public ResponseEntity<PageImpl<ArticleTypeDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "size",defaultValue = "10") Integer size){
              return ResponseEntity.ok(service.pagination(page, size));
          }*/
        @GetMapping("/getByLanguage")
        public ResponseEntity<List<CategoryDTO>>getByLanguage(@PathVariable("name")
                                                            String name){
            return ResponseEntity.ok(service.getRegionLanguage(name));
        }
    }
