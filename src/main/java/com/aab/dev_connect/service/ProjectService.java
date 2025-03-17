package com.aab.dev_connect.service;


import com.aab.dev_connect.dto.ProjectRequestDataType;
import com.aab.dev_connect.dto.ProjectResponseDataType;
import com.aab.dev_connect.exception.ResourceNotFoundException;
import com.aab.dev_connect.mapper.ProjectMapper;
import com.aab.dev_connect.model.Project;
import com.aab.dev_connect.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(final ProjectRepository projectRepository, final ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponseDataType createProject(final ProjectRequestDataType projectRequestData) {
        Project project = projectMapper.ProjectRequestDataTypeToProject(projectRequestData);
        Project createdProject = projectRepository.save(project);
        return projectMapper.ProjectToProjectResponseDataType(createdProject);
    }

    public ProjectResponseDataType getProject(final Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return projectMapper.ProjectToProjectResponseDataType(project);
    }

    public void deleteProject(final Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        projectRepository.delete(project);
    }

    public ProjectResponseDataType updateProject(final Long projectId, final ProjectRequestDataType projectRequestData) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        project.setName(projectRequestData.getName());
        project.setDescription(projectRequestData.getDescription());
        Project updatedProject = projectRepository.save(project);
        return projectMapper.ProjectToProjectResponseDataType(updatedProject);
    }
}
