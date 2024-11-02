package com.irusri.TodoApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusri.TodoApp.dto.request.RequestToDoDTO;
import com.irusri.TodoApp.dto.response.ResponseToDoDTO;
import com.irusri.TodoApp.model.ToDo;
import com.irusri.TodoApp.model.UserPrincipal;
import com.irusri.TodoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/todo")
public class ToDoController {

    @Autowired
    ToDoService service;

    @GetMapping("/all")
    public ResponseEntity<List<ResponseToDoDTO>> getAllToDoItems(@Param("sortingField") String sortingField, @Param("descending") Optional<Boolean> descending){
        if(descending.isEmpty()){
            descending = Optional.of(false);
        }

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ResponseToDoDTO> todos = service.getToDoByUsername(user.getUsername(), sortingField, descending);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("page")
    public ResponseEntity<Page<ResponseToDoDTO>> getToDoByUsername(@Param("offset") int offset, @Param("pageSize") int pageSize,
                                                                   @Param("sortingField") String sortingField,
                                                                   @Param("descending") Optional<Boolean> descending){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<ResponseToDoDTO> todo = service.getToDoByUsername(user.getUsername(), offset, pageSize, sortingField, descending);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseToDoDTO> getToDoById(@PathVariable int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseToDoDTO todo = service.getToDoById(id, user.getUsername());

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseToDoDTO>> getToDoById(@Param("keyword") String keyword){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ResponseToDoDTO> todo = service.searchToDos(user.getUsername(), keyword);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("/searchpages")
    public ResponseEntity<Page<ResponseToDoDTO>> getToDoById(@Param("keyword") String keyword, @Param("offset") int offSet,
                                                             @Param("pagesize") int pagesize, @Param("sortby") String sortby,
                                                             @Param("descending") Optional<Boolean> descending){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<ResponseToDoDTO> todo = service.searchToDos(user.getUsername(), keyword, offSet, pagesize, sortby, descending);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseToDoDTO> createToDo(@RequestBody RequestToDoDTO todo) throws Exception {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseToDoDTO createdToDo = service.createNewToDo(todo, user.getUsername());

        return new ResponseEntity<>(createdToDo, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ResponseToDoDTO> updateToDo(@RequestBody RequestToDoDTO todo, @Param("id") int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseToDoDTO updatedToDo = service.updateToDo(todo, id, user.getUsername());

        return new ResponseEntity<>(updatedToDo, HttpStatus.CREATED);
    }

    @PatchMapping("/completion")
    public ResponseEntity<ResponseToDoDTO> updateCompletionStatus(@Param("id") int id,  @Param("status") String status){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseToDoDTO updatedToDo = service.trackTaskCompletion(user.getUsername(), id, status);

        return new ResponseEntity<>(updatedToDo, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteToDo(@RequestParam int id){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean deleteStatus = service.deleteToDo(id, user.getUsername());

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
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
