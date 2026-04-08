package com.sossbar.projects.service;

import com.sossbar.global.common.code.ErrorCode;
import com.sossbar.global.common.exception.BusinessException;
import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
import com.sossbar.projects.entity.Project;
import com.sossbar.projects.enums.ProjectStatus;
import com.sossbar.projects.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request, String imageUrl) {
        String projectLink = UUID.randomUUID().toString();

        Project project = Project.builder()
                .projectName(request.getProjectName())
                .host(request.getHost())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .projectLink(projectLink)
                .projectImage(imageUrl)
                .projectStatus(ProjectStatus.IN_PROGRESS)
                .build();

        return toResponse(projectRepository.save(project));
    }

    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long projectId) {
        return toResponse(findProjectById(projectId));
    }

    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request, String newImageUrl) {
        Project project = findProjectById(projectId);
        project.update(request.getProjectName(), request.getHost(),
                request.getStartDate(), request.getEndDate(), newImageUrl);
        return toResponse(project);
    }

    @Transactional(readOnly = true)
    public String getProjectImageUrl(Long projectId) {
        return findProjectById(projectId).getProjectImage();
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Project project = findProjectById(projectId);
        projectRepository.delete(project);
    }

    // 공통 조회 메서드
    private Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.PROJECT_NOT_FOUND_EXCEPTION,
                        ErrorCode.PROJECT_NOT_FOUND_EXCEPTION.getMessage() + projectId));
    }

    // Project -> ProjectResponse 변환
    private ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .host(project.getHost())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectLink(project.getProjectLink())
                .projectImage(project.getProjectImage())
                .projectStatus(project.getProjectStatus())
                .build();
    }
}
