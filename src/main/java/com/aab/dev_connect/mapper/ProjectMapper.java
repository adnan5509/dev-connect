package com.aab.dev_connect.mapper;


import com.aab.dev_connect.dto.ProjectRequestDataType;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.dto.TaskForProjectDataType;
import com.aab.dev_connect.model.Project;
import com.aab.dev_connect.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ProjectMapper {

    Project ProjectRequestDataTypeToProject(ProjectRequestDataType projectRequestDataType);

    @Lazy
    @Mapping(target = "tasks", expression = "java(TasksToTaskForProjectDataTypes(project.getTasks()))")
    ProjectResponseDataType ProjectToProjectResponseDataType(Project project);

    TaskForProjectDataType TaskToTaskForProjectDataType(Task task);

    List<TaskForProjectDataType> TasksToTaskForProjectDataTypes(List<Task> tasks);


    List<ProjectResponseDataType> ProjectsToProjectResponseDataTypes(List<Project> projects);


}
