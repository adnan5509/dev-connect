package com.aab.dev_connect.repository;

import com.aab.dev_connect.model.Task;
import com.aab.dev_connect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedTo(User assignedTo);
    List<Task> findByProjectId(Long projectId);
}
