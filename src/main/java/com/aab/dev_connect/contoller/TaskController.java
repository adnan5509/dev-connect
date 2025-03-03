package com.aab.dev_connect.contoller;


import com.aab.dev_connect.dto.TaskRequestDataType;
import com.aab.dev_connect.dto.TaskResponseDataType;
import com.aab.dev_connect.service.TaskService;
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
@RequestMapping("/api/tasks")
@Tag(name = "Task API", description = "Operations related to Tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Create a new Task",
            description = "Creates a new task with the provided details."
    )
    @PostMapping
    public ResponseEntity<TaskResponseDataType> createTask(@RequestBody TaskRequestDataType taskRequestDataType) {
        TaskResponseDataType createdTask = taskService.createTask(taskRequestDataType);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Operation(
            summary = "Get task information",
            description = "Retrieves the information for a task identified by the given taskId."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDataType> getTask(@PathVariable Long id) {
        TaskResponseDataType retrievedTask = taskService.getTask(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(retrievedTask);
    }

}
