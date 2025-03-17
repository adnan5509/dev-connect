package com.aab.dev_connect.service;

import com.aab.dev_connect.dto.TaskRequestDataType;
import com.aab.dev_connect.dto.TaskResponseDataType;
import com.aab.dev_connect.dto.TaskUpdateRequestDataType;
import com.aab.dev_connect.enums.TaskStatus;
import com.aab.dev_connect.exception.ResourceNotFoundException;
import com.aab.dev_connect.mapper.TaskMapper;
import com.aab.dev_connect.model.Task;
import com.aab.dev_connect.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;


    public TaskService(final TaskRepository taskRepository, final TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponseDataType createTask(final TaskRequestDataType taskRequestDataType) {
        Task task = taskMapper.TaskRequestDataTypeToTask(taskRequestDataType);
        Task createdTask = taskRepository.save(task);
        TaskResponseDataType taskResponseDataType = taskMapper.TaskToTaskResponseDataType(createdTask);
        return taskResponseDataType;
    }

    public TaskResponseDataType getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        TaskResponseDataType taskResponseDataType = taskMapper.TaskToTaskResponseDataType(task);
        return taskResponseDataType;
    }

    public void deleteTask(final Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    public TaskResponseDataType updateTask(final Long id, final TaskUpdateRequestDataType taskUpdateRequestDataType) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setTitle(taskUpdateRequestDataType.getTitle());
        task.setDescription(taskUpdateRequestDataType.getDescription());
        task.setStatus(taskUpdateRequestDataType.getStatus());

        Task updatedTask = taskRepository.save(task);

        TaskResponseDataType taskResponseDataType = taskMapper.TaskToTaskResponseDataType(updatedTask);
        return taskResponseDataType;
    }

    public TaskResponseDataType updateTaskStatus(final Long id, final String status) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setStatus(TaskStatus.valueOf(status));

        Task updatedTask = taskRepository.save(task);

        TaskResponseDataType taskResponseDataType = taskMapper.TaskToTaskResponseDataType(updatedTask);
        return taskResponseDataType;

    }
}