package com.mymahallah.mm_user.controller;

import com.mymahallah.mm_user.DTO.RegisterDto;
import com.mymahallah.mm_user.DTO.ResponseDto;
import com.mymahallah.mm_user.entities.User;
import com.mymahallah.mm_user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        try{
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserbyId(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(userService.getUserbyId(id), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> createUser(@RequestBody RegisterDto request){
        try{
            return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
