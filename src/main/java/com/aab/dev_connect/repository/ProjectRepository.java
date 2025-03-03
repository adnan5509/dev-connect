package com.aab.dev_connect.repository;

import com.aab.dev_connect.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
