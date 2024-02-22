package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> crete(@RequestBody ArticleTypeDTO dto){
        return ResponseEntity.ok(articleTypeService.crete(dto));
    }
    @PostMapping("/update")
    public ResponseEntity<Boolean>update(@PathVariable("id") Integer id,
                                         @RequestBody ArticleTypeDTO dto){
        return ResponseEntity.ok(articleTypeService.update(id,dto));
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleTypeService.delete(id));
    }
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size",defaultValue = "10") Integer size){
        return ResponseEntity.ok(articleTypeService.pagination(page, size));
    }
    @GetMapping("/getByLanguage")
    public ResponseEntity<List<ArticleTypeDTO>>getByLanguage(@PathVariable("name")
                                                             String name){
        return ResponseEntity.ok(articleTypeService.getLanguage(name));
    }
}
