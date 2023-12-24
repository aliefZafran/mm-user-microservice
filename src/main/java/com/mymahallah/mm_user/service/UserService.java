package com.mymahallah.mm_user.service;

import com.mymahallah.mm_user.DTO.RegisterDto;
import com.mymahallah.mm_user.DTO.ResponseDto;
import com.mymahallah.mm_user.entities.ERole;
import com.mymahallah.mm_user.entities.Role;
import com.mymahallah.mm_user.entities.User;
import com.mymahallah.mm_user.repository.RoleRepository;
import com.mymahallah.mm_user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //add exception handling
    public Optional<User> getUserbyId(int id){
        return userRepository.findById(id);
    }

    //later refactor to return a response dto
    public ResponseDto createUser(RegisterDto user) {
        log.info("UserService: attempting user registration");

        String matricNo = user.getMatricNo().toString();
        String userEmail = user.getEmail().split("@")[0];
        String lastFourDigits = matricNo.substring(Math.max(0, matricNo.length() - 4));

        String initPw = lastFourDigits + userEmail;

        User newUser = new User(user.getMatricNo(), user.getEmail(), initPw);
        Set<String> strRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "staff":
                        Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(staffRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
        log.info("User created successfully");
        return modelMapper.map(newUser, ResponseDto.class);
    }
}
