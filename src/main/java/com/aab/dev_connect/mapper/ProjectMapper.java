package com.aab.dev_connect.mapper;


import com.aab.dev_connect.dto.ProjectRequestDataType;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.dto.TaskForProjectDataType;
import com.aab.dev_connect.model.Project;
import com.aab.dev_connect.model.Task;
import com.aab.dev_connect.model.User;
import com.aab.dev_connect.utility.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ProjectMapper {

    @Mapping(target = "owner", expression = "java(getCurrentUser())")
    Project ProjectRequestDataTypeToProject(ProjectRequestDataType projectRequestDataType);

    @Lazy
    @Mapping(target = "tasks", expression = "java(TasksToTaskForProjectDataTypes(project.getTasks()))")
    @Mapping(target = "ownerId", source = "owner.id")
    ProjectResponseDataType ProjectToProjectResponseDataType(Project project);

    List<ProjectResponseDataType> ProjectsToProjectResponseDataTypes(List<Project> project);


    TaskForProjectDataType TaskToTaskForProjectDataType(Task task);

    List<TaskForProjectDataType> TasksToTaskForProjectDataTypes(List<Task> tasks);

     default User getCurrentUser() {
        return SecurityUtil.getCurrentUser();
    }
}
