package com.binary.hospital.controller;

import com.binary.hospital.Dtos.AuthenticationRequestDto;
import com.binary.hospital.Dtos.AuthenticationResponseDto;
import com.binary.hospital.config.EnrolementDetailService;
import com.binary.hospital.config.JwtUtil;
import com.binary.hospital.model.Enrolement;
import com.binary.hospital.service.EnrolementServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrolement")

public class EnrolementController {

    @Autowired
    private EnrolementServiceImple enrolementServiceImple;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EnrolementDetailService enrolementDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Object> registerMember(@RequestBody Enrolement enrolement){
        return  new ResponseEntity<>(enrolementServiceImple.createMember(enrolement), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> createMemberAuthToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword())
            );
        }catch (Exception e){
            throw  new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = enrolementDetailService.loadUserByUsername(authenticationRequestDto.getUsername());
        final  String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponseDto(jwt), HttpStatus.ACCEPTED);
    }



}
