package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
  private   AuthService authService;
    @PostMapping("/crete")
    public ResponseEntity<ProfileDTO>login(@RequestBody AuthDTO dto){
        return ResponseEntity.ok(authService.authLogin(dto));
    }
    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }
    @GetMapping("/verification/email/{id}")
    public ResponseEntity<String> emailVerification(@PathVariable("id") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }


}
