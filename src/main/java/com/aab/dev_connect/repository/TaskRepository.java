package com.aab.dev_connect.repository;

import com.aab.dev_connect.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
