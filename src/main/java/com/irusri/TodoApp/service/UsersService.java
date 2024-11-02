package com.irusri.TodoApp.service;

import com.irusri.TodoApp.dto.request.RequestUserDTO;
import com.irusri.TodoApp.dto.response.ResponseUserDTO;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @Autowired
    private UsersRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private ResponseUserDTO convertToResponseDTO(Users user){
        return new ResponseUserDTO(user.getId(), user.getUsername());
    }

    private Users convertToEntity(RequestUserDTO userdto){
        Users convertedEntity = new Users();
        convertedEntity.setUsername(userdto.getUsername());

        return convertedEntity;
    }

    public ResponseUserDTO registerUser(RequestUserDTO user){
        logger.info("Request from Client for new user creation!");

        Users convertedUserEntity = convertToEntity(user);

        convertedUserEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        Users newUser = repo.save(convertedUserEntity);
        logger.info("New user created successfully. New user username: " + convertedUserEntity.getUsername());

        return convertToResponseDTO(newUser);
    }

    public String verify(RequestUserDTO user) {
//        Users user = convertToEntity(userDTO);
        logger.info(user.getUsername() + " trying to authenticate!");
        Authentication authentication =
                authManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            logger.info(user.getUsername() + " successfully authenticated!");
            return jwtService.generateToken(user.getUsername());
        }
        return null;
    }

    public ResponseUserDTO updatePassword(RequestUserDTO userDTO) throws Exception {
        Users updatePwUser = repo.findByUsername(userDTO.getUsername());

        System.out.println("Checking null: " + updatePwUser);

        if (updatePwUser == null) {
            throw new Exception("User not found!"); // Add this check if the user is not found
        }

        if(!Objects.equals(userDTO.getNewPassword(), userDTO.getConfirmNewPassword()) && !Objects.equals(passwordEncoder.encode(userDTO.getPassword()), updatePwUser.getPassword())){
            throw new Exception("Error occurred while updating the password! Check your credentials.");
        }

        updatePwUser.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));

        System.out.println("Checking null: " + updatePwUser);

        Users finalPwUpdatedUser = repo.save(updatePwUser);

        return convertToResponseDTO(finalPwUpdatedUser);
    }

    public boolean deleteUser(UserPrincipal user) throws Exception {
        try{
            Users userObj = repo.findByUsername(user.getUsername());
            repo.deleteById(userObj.getId());
            return true;
        } catch(Exception ex){
            return false;
        }
    }
}
