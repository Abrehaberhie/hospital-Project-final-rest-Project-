package com.binary.hospital.config;

import com.binary.hospital.model.Enrolement;
import com.binary.hospital.service.EnrolementServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EnrolementDetailService implements UserDetailsService {

    @Autowired
  private EnrolementServiceImple enrolementServiceImple;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String enroluserEmail = null;
        String enroluserPassword = null;
        List<GrantedAuthority> authorities = null;
        Enrolement enrolement=enrolementServiceImple.getMembersByEmail(username);
        if(enrolement==null)
        {
            throw new UsernameNotFoundException("enrolement is not found with email : " + username );
        }else{
            enroluserEmail=enrolement.getEmail();
            enroluserPassword=enrolement.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(enrolement.getRole()));
        }
        return new User(enroluserEmail, enroluserPassword, authorities);
    }
}
