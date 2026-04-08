package com.sossbar.projects.service;

import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
import com.sossbar.projects.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request, String imageUrl) {
        // 1. request + imageUrl + 초기 projectStatus(IN_PROGRESS) + 랜덤 uuid로 projectLink 생성하여 Project 엔티티 빌드
        // 2. projectRepository.save()로 저장
        // 3. 저장된 Project를 ProjectResponse로 변환하여 반환
        throw new UnsupportedOperationException("구현 예정");
    }

    public ProjectResponse getProject(Long projectId) {
        // 1. projectRepository.findById(projectId)로 조회
        // 2. 없으면 커스텀 예외 던지기 (ex. ProjectNotFoundException)
        // 3. 조회된 Project를 ProjectResponse로 변환하여 반환
        throw new UnsupportedOperationException("구현 예정");
    }

    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request, String newImageUrl) {
        // 1. projectRepository.findById(projectId)로 조회
        // 2. 없으면 커스텀 예외 던지기 (ex. ProjectNotFoundException)
        // 3. project.update()로 변경 (Dirty Checking으로 커밋 시 자동 반영)
        // 4. 수정된 Project를 ProjectResponse로 변환하여 반환
        throw new UnsupportedOperationException("구현 예정");
    }

    @Transactional
    public String getProjectImageUrl(Long projectId) {
        // 1. projectRepository.findById(projectId)로 조회
        // 2. 없으면 커스텀 예외 던지기 (ex. ProjectNotFoundException)
        // 3. 기존 projectImage url 반환 (Facade에서 S3 삭제 시 사용)
        throw new UnsupportedOperationException("구현 예정");
    }

    @Transactional
    public void deleteProject(Long projectId) {
        // 1. projectRepository.findById(projectId)로 조회
        // 2. 없으면 커스텀 예외 던지기 (ex. ProjectNotFoundException)
        // 3. projectRepository.delete()로 DB에서 삭제
        throw new UnsupportedOperationException("구현 예정");
    }
}
