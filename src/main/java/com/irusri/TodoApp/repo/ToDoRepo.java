package com.irusri.TodoApp.repo;

import com.irusri.TodoApp.model.ToDo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToDoRepo extends JpaRepository<ToDo, Integer> {
    @Query("SELECT td FROM ToDo td WHERE " +
            "td.assignedUser.username = :username"
    )
    List<ToDo> findByUsername(String username);

    @Query("SELECT td FROM ToDo td WHERE " +
            "td.assignedUser.username = :username"
    )
    List<ToDo> findByUsername(String username, Sort sort);

    @Query("SELECT td FROM ToDo td WHERE " +
            "td.assignedUser.username = :username"
    )
    Page<ToDo> findByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT td FROM ToDo td WHERE " +
            "(LOWER(td.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.priority) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.dueDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.timezone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.createdDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "CAST(td.updatedDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.assignedUser.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.completionDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.tags) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.reminder as String) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND td.assignedUser.username = :username")
    List<ToDo> searchToDosByKeyword(@Param("username") String username, @Param("keyword") String keyword);

    @Query("SELECT td FROM ToDo td WHERE " +
            "(LOWER(td.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.priority) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.dueDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.timezone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.createdDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "CAST(td.updatedDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(td.assignedUser.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.completionDate AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(td.tags) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(td.reminder as String) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND td.assignedUser.username = :username")
    Page<ToDo> searchToDosByKeyword(@Param("username") String username, @Param("keyword") String keyword, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM ToDo td WHERE " +
            "td.assignedUser.username = :username")
    int deleteByUsername(String username);
}
