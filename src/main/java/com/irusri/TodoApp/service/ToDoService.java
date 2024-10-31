package com.irusri.TodoApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusri.TodoApp.CustomExceptions.DBFaliureException;
import com.irusri.TodoApp.CustomExceptions.NoItemsFoundException;
import com.irusri.TodoApp.model.ToDo;
import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.ToDoRepo;
import com.irusri.TodoApp.repo.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class ToDoService {
    private static final Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @Autowired
    ToDoRepo repo;

    @Autowired
    UsersRepo userrepo;

    public List<ToDo> getToDoByUsername(String username, String sortingField, Optional<Boolean> descending){
        boolean isDescendingRequested = descending.orElse(false);

        List<ToDo> todos = null;

        if(sortingField != null){
            Sort sort = isDescendingRequested ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();

            logger.info(username + " is trying to retrieve particular user's all ToDo items with sorting enabled. Sorting direction: " + (isDescendingRequested ? "descending" : "ascending"));
            todos = repo.findByUsername(username, sort);
        }
        else{
            logger.info(username + " is trying to retrieve particular user's all ToDo items.");
            todos = repo.findByUsername(username);
        }

        if(todos.isEmpty()){
            //Error logging is managed in the Exception handler.
            throw new NoItemsFoundException("No items found for the given user: " + username);
        }
        logger.info(username + " successfully retrieved particular user's all ToDo items!");
        return todos;
    }

    public Page<ToDo> getToDoByUsername(String username, int offset, int pageSize, String sortingField, Optional<Boolean> descending){
        boolean isDescendingRequested = descending.orElse(false);

        Sort sort = isDescendingRequested ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();

        logger.info(username + " trying to retrieve particular user's all ToDo items with pagination enabled and sorting. Sorting direction: " + (isDescendingRequested ? "descending" : "ascending"));
        Page<ToDo> todos = repo.findByUsername(username, PageRequest.of(offset, pageSize).withSort(sort));

        if(todos.isEmpty()){
            throw new NoItemsFoundException("No items found for the given user: " + username);
        }

        logger.info(username + " successfully retrieved particular user's all ToDo items with pagination enabled!");
        return todos;
    }

    public ToDo getToDoById(int id, String username) {
        logger.info(username + " trying to retrieve a ToDo item by id!");
        ToDo todo = repo.findById(id).orElse(null);
        if(todo == null){
            throw new NoItemsFoundException("Item with given ID not found. Input value of ID: " + id);
        }

        logger.info(username + " successfully accessed a ToDo item by id!");
        return todo;
    }

    public List<ToDo> searchToDos(String username, String keyword){
        logger.info(username + " trying to retrieve ToDo items by keyword. Keyword: " + keyword);
        List<ToDo> todos = repo.searchToDosByKeyword(username, keyword);
        if(todos.isEmpty()){
            throw new NoItemsFoundException("No items found for the given user with given keywords!");
        }

        logger.info(username + " retrieved ToDo items by keyword successfully!");
        return todos;
    }

    public Page<ToDo> searchToDos(String username, String keyword, int offset, int pageSize, String sortingField, Optional<Boolean> descending){
        logger.info(username + " trying to retrieve ToDo items by keyword and pagination and sorting enabled. Keyword: " + keyword);
        boolean isDescendingRequested = descending.orElse(false);

        Sort sort = isDescendingRequested ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();

        logger.info(username + " trying to retrieve ToDo items by keyword and pagination and sorting enabled. Keyword: " + keyword + "Sorting direction: " + (isDescendingRequested ? "descending" : "ascending"));
        Page<ToDo> todos = repo.searchToDosByKeyword(username, keyword, PageRequest.of(offset, pageSize).withSort(sort));

        if(todos.isEmpty()){
            throw new NoItemsFoundException("No ToDo items found for the particular user with given keyword!");
        }

        logger.info(username + " retrieved ToDo items by keyword successfully!");
        return todos;
    }

    public ToDo createNewToDo(ToDo todo, String username) throws Exception {
        logger.info(username + " trying to create a new ToDo item!");
        Users user = userrepo.findByUsername(username);
        ZoneId zoneId = ZoneId.of(todo.getTimezone());
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

        todo.setAssignedUser(user);
        todo.setCreatedDate(currentTime);
        todo.setUpdatedDate(currentTime);

        ToDo newItem = null;

        try{
            logger.info(username + " creating a new ToDo item in the Database!");
            newItem = repo.save(todo);
        } catch(Exception ex){
            throw new DBFaliureException(ex.getMessage());
        }

        logger.info(username + " successfully created a new ToDo item in the Database!");
        return newItem;
    }

    public ToDo updateToDo(ToDo todo, int id, String username) {
        logger.warn(username + " trying to update a new ToDo item!");
        String newTitle = todo.getTitle();
        String description = todo.getDescription();
        String priority = todo.getPriority();
        ZonedDateTime dueDate = todo.getDueDate();
//        String timezzone = todo.getTimezone();
        String category = todo.getCategory();
        ZonedDateTime reminder = todo.getReminder();
        ZonedDateTime completionDate = todo.getCompletionDate();

        ToDo oldToDo = repo.findById(id).orElse(null);
        if(oldToDo == null){
            throw new NoItemsFoundException("No ToDo item found for the given item id!");
        }
        if (newTitle != null){
            oldToDo.setTitle(newTitle);
        }
        if(description != null){
            oldToDo.setDescription(description);
        }
        if(priority != null){
            oldToDo.setPriority(priority);
        }
        if(dueDate != null){
            oldToDo.setDueDate(dueDate);
        }
        if(category != null){
            oldToDo.setCategory(category);
        }
        if(reminder != null){
            oldToDo.setReminder(reminder);
        }
        if(completionDate != null){
            oldToDo.setCompletionDate(completionDate);
        }

        ZoneId zoneId = ZoneId.of(todo.getTimezone());
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

        oldToDo.setUpdatedDate(currentTime);
        oldToDo.setUpdatedDate(currentTime);
        oldToDo.setUpdatedDate(currentTime);

        ToDo updatedItem = null;

        try{
            logger.warn(username + " updating a ToDo item in  the Database!");
            updatedItem = repo.save(todo);
        } catch(Exception ex){
            throw new DBFaliureException(ex.getMessage());
        }

        logger.warn(username + " successfully updated a ToDo item in  the Database!");
        return updatedItem;
    }

    public boolean deleteToDo(int id, String username) {
        logger.warn(username + " trying to delete a ToDo item!");
        if(repo.findById(id).isEmpty()){
            throw new NoItemsFoundException("No ToDo item found for the given item id!");
        }

        try{
            logger.warn(username + " deleting a ToDo item in the Database!");
            repo.deleteById(id);
        } catch(Exception ex){
            throw new DBFaliureException(ex.getMessage());
        }

        logger.warn(username + " successfully deleted a ToDo item in the Database!");
        return true;
    }

    public String deleteAllToDos(String username) throws JsonProcessingException {
        logger.warn(username + " trying to delete all their ToDo items!");
        Map<String, Object> map = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        if(repo.findByUsername(username).isEmpty()){
            throw new NoItemsFoundException("No items found for the given ToDo id!");
        }

        try{
            logger.warn(username + " deleting all their ToDo items in the Database!");
            int deletedCount = repo.deleteByUsername(username);

            map.put("status", true);
            map.put("message", "All ToDo items are deleted!");
            map.put("deletedCount",  deletedCount);

            logger.warn(username + " successfully deleted all their ToDo items in the Database!");
            return objectMapper.writeValueAsString(map);
        } catch(Exception ex){
            throw new DBFaliureException(ex.getMessage());
        }
    }
}
