package com.irusri.TodoApp.dto.request;

import com.irusri.TodoApp.model.Users;

import java.time.ZonedDateTime;
import java.util.Optional;

public class RequestToDoDTO {
    private int id;
    private String category;
    private ZonedDateTime completionDate;
    private String description;
    private ZonedDateTime dueDate;
    private String priority;
    private ZonedDateTime reminder;
    private String status;
    private String tags;
    private String timezone;
    private String title;
    private Users assignedUser;
    private String sortingField;
    private Optional<Boolean> descending;

    public RequestToDoDTO(int id, String category, ZonedDateTime completionDate, String description, ZonedDateTime dueDate, String priority, ZonedDateTime reminder, String status, String tags, String timezone, String title, Users assignedUser) {
        this.id = id;
        this.category = category;
        this.completionDate = completionDate;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.reminder = reminder;
        this.status = status;
        this.tags = tags;
        this.timezone = timezone;
        this.title = title;
        this.assignedUser = assignedUser;
    }

    public RequestToDoDTO(int id, String category, ZonedDateTime completionDate, String description, ZonedDateTime dueDate, String priority, ZonedDateTime reminder, String status, String tags, String timezone, String title, Users assignedUser, String sortingField, Optional<Boolean> descending) {
        this.id = id;
        this.category = category;
        this.completionDate = completionDate;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.reminder = reminder;
        this.status = status;
        this.tags = tags;
        this.timezone = timezone;
        this.title = title;
        this.assignedUser = assignedUser;
        this.sortingField = sortingField;
        this.descending = descending;
    }

    public RequestToDoDTO(String category, ZonedDateTime completionDate, String description, ZonedDateTime dueDate, String priority, ZonedDateTime reminder, String status, String tags, String timezone, String title, Users assignedUser) {
        this.category = category;
        this.completionDate = completionDate;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.reminder = reminder;
        this.status = status;
        this.tags = tags;
        this.timezone = timezone;
        this.title = title;
        this.assignedUser = assignedUser;
    }

    public RequestToDoDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ZonedDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(ZonedDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ZonedDateTime getReminder() {
        return reminder;
    }

    public void setReminder(ZonedDateTime reminder) {
        this.reminder = reminder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(Users assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getSortingField() {
        return sortingField;
    }

    public void setSortingField(String sortingField) {
        this.sortingField = sortingField;
    }

    public Optional<Boolean> isDescending() {
        return descending;
    }

    public void setDescending(Optional<Boolean> descending) {
        this.descending = descending;
    }
}
