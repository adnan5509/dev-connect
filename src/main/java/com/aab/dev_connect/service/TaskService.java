package com.aab.dev_connect.service;

import com.aab.dev_connect.dto.TaskRequestDataType;
import com.aab.dev_connect.dto.TaskResponseDataType;
import com.aab.dev_connect.exception.ResourceNotFoundException;
import com.aab.dev_connect.mapper.TaskMapper;
import com.aab.dev_connect.model.Task;
import com.aab.dev_connect.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private TaskMapper taskMapper;


    public TaskService(final TaskRepository taskRepository,final TaskMapper taskMapper) {
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
}
