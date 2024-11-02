package com.irusri.TodoApp.controller;

import com.irusri.TodoApp.dto.request.RequestUserDTO;
import com.irusri.TodoApp.dto.response.ResponseUserDTO;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseUserDTO> registerUser(@RequestBody RequestUserDTO user){
        ResponseUserDTO registeredUser = service.registerUser(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", produces = {"application/json"})
    public ResponseEntity<String> loginUser(@RequestBody RequestUserDTO user) throws Exception {
        String token = service.verify(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTest(){
        return "JWT validating correctly!";
    }

    @PatchMapping("update-password")
    public ResponseEntity<ResponseUserDTO> updatePassword(@RequestBody RequestUserDTO userDTO) throws Exception {
        ResponseUserDTO result = service.updatePassword(userDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser() throws Exception {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean result = service.deleteUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
