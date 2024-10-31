package com.irusri.TodoApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusri.TodoApp.model.ToDo;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    ToDoService service;

    @GetMapping("{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ToDo todo = service.getToDoById(id, user.getUsername());

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ToDo>> getAllToDoItems(@Param("sortingField") String sortingField, @Param("descending") boolean descending){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ToDo> todos = service.getToDoByUsername(user.getUsername(), sortingField, Optional.of(descending));

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo) throws Exception {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ToDo createdToDo = service.createNewToDo(todo, user.getUsername());

        return new ResponseEntity<>(createdToDo, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo todo, @PathVariable int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ToDo updatedToDo = service.updateToDo(todo, id, user.getUsername());

        return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean deleteStatus = service.deleteToDo(id, user.getUsername());

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllTodos() throws JsonProcessingException {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String result = service.deleteAllToDos(user.getUsername());

        ObjectMapper objectMapper = new ObjectMapper();

        Map map;
        map = objectMapper.readValue(result, Map.class);
        boolean status = (boolean) map.get("status");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
