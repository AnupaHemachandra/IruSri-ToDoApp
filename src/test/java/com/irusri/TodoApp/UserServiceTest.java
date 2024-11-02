package com.irusri.TodoApp;

import com.irusri.TodoApp.dto.request.RequestUserDTO;
import com.irusri.TodoApp.dto.response.ResponseUserDTO;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.UsersRepo;
import com.irusri.TodoApp.service.JWTService;
import com.irusri.TodoApp.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UsersService service;

    @MockBean
    private UsersRepo repo;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTService jwtService;

    @Test
    public void testRegisterUser(){
        RequestUserDTO userDTO = new RequestUserDTO("testUser", "testPassword");
        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword("testPassword");

//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(repo.save(any(Users.class))).thenReturn(user);

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ResponseUserDTO registeredUser = service.registerUser(userDTO);

        assertNotNull(registeredUser);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        verify(repo).save(any(Users.class));
    }

    @Test
    public void updatePasswordTest() throws Exception {
        String currentPasswordNotEncoded = "testPassword";
        String currentPasswordEncoded = "testEncodedPassword";
        String newPasswordNotEncoded = "newTestPassword";

        RequestUserDTO userDTO = new RequestUserDTO("testUser", currentPasswordNotEncoded, newPasswordNotEncoded, newPasswordNotEncoded);

        Users user = new Users();
        user.setUsername("testUser");
        user.setPassword(currentPasswordEncoded);

        String username = user.getUsername();

        UserPrincipal userprincipal = new UserPrincipal(user);

        System.out.println(userprincipal.getPassword());

        when(repo.findByUsername(userDTO.getUsername())).thenReturn(user);

        when(repo.save(any(Users.class))).thenAnswer(invocation -> {
            Users savedUser = invocation.getArgument(0);
            savedUser.setId(100);
            return savedUser;
        });

        ResponseUserDTO pwUpdateUser = service.updatePassword(userDTO);

        System.out.println(pwUpdateUser.getId());


        assertNotNull(pwUpdateUser);
        assertEquals(username, pwUpdateUser.getUsername());
        assertNotEquals(currentPasswordEncoded, userDTO.getPassword());
        verify(repo).save(user);
    }

//    @Test
//    public void deleteUserTest() throws Exception {
//        String encodedPassword = "$2a$12$8SjJaLZA7uIdifB.qtcvUu.UmWkrLh8Hrqo/YXQJpEM5XsRc.sq7a";
//        String password = "password";
//        Users user = new Users();
//        String username = "user";
//        user.setId(1);
//        user.setUsername(username);
//        user.setPassword(encodedPassword);
//
//        UserPrincipal userprincipal = new UserPrincipal(user);
//
//        when(userprincipal.getPassword()).thenReturn(encodedPassword);
//        when(Objects.equals(anyString(), userprincipal.getPassword())).thenReturn(true);
//
//        when(repo.findByUsername(username)).thenReturn(user);
//
//        boolean status = service.deleteUser(password, userprincipal);
//
//        assertTrue(status);
//        assertNull(repo.findByUsername(username));
//        verify(repo).deleteById(user.getId());
//    }

    @Test
    public void deleteUserTest() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("password");

        UserPrincipal userprincipal = new UserPrincipal(user);

        when(repo.findByUsername(userprincipal.getUsername())).thenReturn(user);

        boolean result = service.deleteUser(userprincipal);

        // Verify
        assertTrue(result);
        verify(repo).deleteById(1);
    }
}
