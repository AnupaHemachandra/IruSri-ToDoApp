package com.irusri.TodoApp.dto.response;

import com.irusri.TodoApp.model.Users;

import java.time.ZonedDateTime;

public class ResponseToDoDTO {
    private int id;
    private String title;
    private String description;
    private String priority;
    private ZonedDateTime dueDate;
    private String status;
    private String timezone;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
    private String category;
    private ZonedDateTime completionDate;
    private String tags;
    private ZonedDateTime reminder;

    public ResponseToDoDTO(int id, String title, String description, String priority, ZonedDateTime dueDate, String status, String timezone, ZonedDateTime createdDate, ZonedDateTime updatedDate, String category, ZonedDateTime completionDate, String tags, ZonedDateTime reminder) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.timezone = timezone;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category = category;
        this.completionDate = completionDate;
        this.tags = tags;
        this.reminder = reminder;
    }

    public ResponseToDoDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ZonedDateTime getReminder() {
        return reminder;
    }

    public void setReminder(ZonedDateTime reminder) {
        this.reminder = reminder;
    }
}
