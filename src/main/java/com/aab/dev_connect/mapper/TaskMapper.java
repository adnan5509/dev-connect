package com.aab.dev_connect.mapper;


import com.aab.dev_connect.dto.ProjectForTaskDataType;
import com.aab.dev_connect.dto.TaskRequestDataType;
import com.aab.dev_connect.dto.TaskResponseDataType;
import com.aab.dev_connect.exception.ResourceNotFoundException;
import com.aab.dev_connect.model.Project;
import com.aab.dev_connect.model.Task;
import com.aab.dev_connect.repository.ProjectRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    @Autowired
    protected ProjectRepository projectRepository;

    @Mapping(target = "project", source = "projectId")
    @Mapping(target = "status", expression = "java(com.aab.dev_connect.enums.TaskStatus.PENDING)")
    public abstract Task TaskRequestDataTypeToTask(TaskRequestDataType requestDataType);

    @Lazy
    @Mapping(target = "project", expression = "java(MapProjectToProjectForTaskDataType(task.getProject()))")
    public abstract TaskResponseDataType TaskToTaskResponseDataType(Task task);

    public abstract List<TaskResponseDataType> TasksToTaskResponseDataTypes(List<Task> tasks);

    protected Project map(Long projectId) {
        if (projectId == null) {
            return null;
        } else {
            return projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        }
    }

    public abstract ProjectForTaskDataType MapProjectToProjectForTaskDataType(Project project);

}
