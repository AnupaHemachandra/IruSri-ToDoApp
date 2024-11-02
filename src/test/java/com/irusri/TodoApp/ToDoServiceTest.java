package com.irusri.TodoApp;

import com.irusri.TodoApp.dto.request.RequestToDoDTO;
import com.irusri.TodoApp.dto.response.ResponseToDoDTO;
import com.irusri.TodoApp.model.ToDo;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.ToDoRepo;
import com.irusri.TodoApp.repo.UsersRepo;
import com.irusri.TodoApp.service.JWTService;
import com.irusri.TodoApp.service.ToDoService;
import com.irusri.TodoApp.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
//import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UsersService usersService;

    @MockBean
    private ToDoRepo repo;

    @MockBean
    private UsersRepo usersRepo;

    @Test
    public void testGetToDoByUsername(){
        ToDo todo = new ToDo();
        todo.setId(1);
        todo.setTitle("Test Title");
        todo.setDescription("Test Description");
        todo.setPriority("High");
        todo.setDueDate(ZonedDateTime.now());  // Setting current date-time as a test value
        todo.setStatus("On Going");
        todo.setTimezone("Asia/Colombo");
        todo.setCreatedDate(ZonedDateTime.now());  // Setting current date-time
        todo.setUpdatedDate(ZonedDateTime.now());  // Setting current date-time
        todo.setCategory("Work");

        ResponseToDoDTO tododto = new ResponseToDoDTO();
        tododto.setId(1);
        tododto.setTitle("Test Title");
        tododto.setDescription("Test Description");
        tododto.setPriority("High");
        tododto.setDueDate(ZonedDateTime.now());  // Setting current date-time as a test value
        tododto.setStatus("On Going");
        tododto.setTimezone("Asia/Colombo");
        tododto.setCreatedDate(ZonedDateTime.now());  // Setting current date-time
        tododto.setUpdatedDate(ZonedDateTime.now());  // Setting current date-time
        tododto.setCategory("Work");

        // Assuming you have a `Users` object for the `assignedUser`
        Users testUser = new Users();
        testUser.setId(1);
        testUser.setUsername("testUser");
        todo.setAssignedUser(testUser);  // Assigning the test user

        todo.setCompletionDate(null);  // Assuming it's not completed yet
        todo.setTags("tag1,tag2");
        todo.setReminder(null);  // No reminder set for now

        tododto.setAssignedUser(testUser);  // Assigning the test user

        tododto.setCompletionDate(null);  // Assuming it's not completed yet
        tododto.setTags("tag1,tag2");
        tododto.setReminder(null);

        when(repo.findById(1)).thenReturn(Optional.of(todo));

        ResponseToDoDTO retreivedTodoDto = toDoService.getToDoById(1, "testUSer");

        assertEquals(tododto.getTitle(), retreivedTodoDto.getTitle());
    }
}
