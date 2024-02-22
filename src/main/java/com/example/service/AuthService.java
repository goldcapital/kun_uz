package com.example.service;

import com.example.dto.AuthDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MDUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private MailSenderServicer mailSender;
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO authLogin(AuthDTO profile) { // login
        Optional<ProfileEntity> optional = profileRepository.findByEmailOrPhoneAndPassword(
                profile.getEmail(), profile.getPhone(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            throw new AppBadException("Email or Password is wrong");
        }
        ProfileEntity entity=optional.get();
        if(entity.getStatus().equals(ProfileStatus.BlOCK)){
            throw new AppBadException("Email or Password is wrong");
        }
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));

        return dto;
    }

    public Boolean registration(RegistrationDTO dto) {
            // validation
            // check
            Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
            if (optional.isPresent()) {
                if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                    profileRepository.deleteById(optional.get().getId());
                } else {
                    throw new AppBadException("Email exists");
                }
            }
            // create
            ProfileEntity entity = new ProfileEntity();
            entity.setName(dto.getName());
            entity.setSurname(dto.getSurname());
            entity.setEmail(dto.getEmail());
            entity.setPassword(MDUtil.encode(dto.getPassword()));
            entity.setStatus(ProfileStatus.REGISTRATION);
            entity.setRole(ProfileRole.USER);
            profileRepository.save(entity);
            String jwt=JWTUtil.encodeForEmail(entity.getId());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8080/auth/verification/email/" + jwt;
      //  mailSender.sendEmail(entity.getEmail(),"Complete registration",text);
            //send verification code (email/sms)
            return true;
        }

    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
    }

}

