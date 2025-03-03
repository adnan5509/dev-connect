package com.aab.dev_connect.contoller;


import com.aab.dev_connect.dto.ProjectRequestDataType;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
