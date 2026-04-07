package com.sossbar.projects.service;

import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    // TODO: private final ProjectRepository projectRepository;
    // TODO: private final S3Uploader s3Uploader; (이미지 업로드 처리)

    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request, MultipartFile image) {
        // TODO: 이미지 S3 업로드 후 url 저장
        throw new UnsupportedOperationException("구현 예정");
    }

    public ProjectResponse getProject(Long projectId) {
        // TODO: 구현 예정
        throw new UnsupportedOperationException("구현 예정");
    }

    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request, MultipartFile image) {
        // TODO: 이미지 S3 업로드 후 url 수정
        throw new UnsupportedOperationException("구현 예정");
    }

    @Transactional
    public void deleteProject(Long projectId) {
        // TODO: 구현 예정
        throw new UnsupportedOperationException("구현 예정");
    }
}
