package com.irusri.TodoApp.controller;

import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping(value = "/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user){
        Users registeredUser = service.registerUser(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = {"application/json"})
    public ResponseEntity<String> loginUser(@RequestBody Users user) throws Exception {
        String token = service.verify(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTest(){
        return "JWT validating correctly!";
    }

    @PutMapping("update-password")
    public ResponseEntity<?> updatePassword(@RequestBody String newPassword, @RequestBody String confirmNewPassword, @RequestBody String currentPassword) throws Exception {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean result = service.updatePassword(newPassword, confirmNewPassword, currentPassword, user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(@RequestBody String password) throws Exception {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean result = service.deleteUser(password, user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
