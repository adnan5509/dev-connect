package com.aab.dev_connect.contoller;


import com.aab.dev_connect.dto.ProjectRequestDataType;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project API", description = "Operations related to Projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(
            summary = "Create a new Project",
            description = "Creates a new project with the provided details. The request body should include the name and description of the project"
    )
    @PostMapping
    public ResponseEntity<ProjectResponseDataType> createProject(@RequestBody ProjectRequestDataType projectRequestData) {
        ProjectResponseDataType createdProject = projectService.createProject(projectRequestData);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @Operation(
            summary = "Get project information",
            description = "Retrieves the information for a project identified by the given projectId."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDataType> getProject(@PathVariable Long id) {
        ProjectResponseDataType retrievedProject = projectService.getProject(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(retrievedProject);
    }

    @Operation(
            summary = "Delete a project",
            description = "Deletes the project identified by the provided projectId."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Update project information",
            description = "Updates the information for a project identified by the provided projectId."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDataType> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDataType projectRequestData) {
        ProjectResponseDataType updatedProject = projectService.updateProject(id, projectRequestData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
    }

    @Operation(
            summary = "Get all projects of a user",
            description = "Retrieves all projects of a user identified by the given userId."
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectResponseDataType>> getProjectsByUserId(@PathVariable Long userId) {
        List<ProjectResponseDataType> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @Operation(
            summary = "Update project status",
            description = "Updates the status of an existing project identified by projectId."
    )
    @PutMapping("/{id}/status")
    public ResponseEntity<ProjectResponseDataType> updateProjectStatus(@PathVariable Long id, @RequestBody String status) {
        ProjectResponseDataType updatedProject = projectService.updateProjectStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
    }

}
