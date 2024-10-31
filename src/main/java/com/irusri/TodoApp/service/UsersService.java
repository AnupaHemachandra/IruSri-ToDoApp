package com.irusri.TodoApp.service;

import com.irusri.TodoApp.CustomExceptions.CustomerAlreadyExistsException;
import com.irusri.TodoApp.CustomExceptions.NoSuchCustomerExistsException;
import com.irusri.TodoApp.CustomExceptions.UserValidationError;
import com.irusri.TodoApp.model.ToDo;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    public Users registerUser(Users user){
        logger.info("Request from Client fro new user creation!");
        Users existingUser = repo.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new CustomerAlreadyExistsException("A user with the given username already exists. Given username: " + user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users newUser = repo.save(user);
        logger.info("New user created successfully. New user username: " + user.getUsername());

        return newUser;
    }

    public String verify(Users user) {
        try{
            logger.info(user.getUsername() + " trying to authenticate!");
            Users existingUser = repo.findByUsername(user.getUsername());
            if(existingUser == null){
                throw new NoSuchCustomerExistsException(user.getUsername() + ": No such user found in the Database!");
            }
            Authentication authentication =
                    authManager
                            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(!authentication.isAuthenticated()){
                throw new Exception("Falied to authenticate the user!");
            }
            logger.info(user.getUsername() + " successfully authenticated!");
            return jwtService.generateToken(user.getUsername());
        } catch(Exception ex){
            throw new UserValidationError(ex.getMessage());
        }
    }

    public boolean updatePassword(String newPassword, String confirmNewPassword, String currentPassword, UserPrincipal userprincipal) throws Exception {
        Users updatePwUser = repo.findByUsername(userprincipal.getUsername());
        updatePwUser.setPassword(passwordEncoder.encode(newPassword));

        if(!Objects.equals(newPassword, confirmNewPassword) && !Objects.equals(passwordEncoder.encode(currentPassword), updatePwUser.getPassword())){
            throw new Exception("Error occurred while updating the password! Check your credentials.");
        }
        Users pwUpdatedUser = repo.save(updatePwUser);

        if(repo.findById(pwUpdatedUser.getId()).isPresent()){
            return true;
        }
        throw new Exception("Failed to update password!");
    }

    public boolean deleteUser(String password, UserPrincipal user) throws Exception {
        if(Objects.equals(passwordEncoder.encode(password), user.getPassword())){
            Users userObj = repo.findByUsername(user.getUsername());
            repo.deleteById(userObj.getId());

            return true;
        }
        throw new Exception("Invalid password!");
    }
}
