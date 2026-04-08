package com.sossbar.projects.facade;

import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
import com.sossbar.projects.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;
    // TODO: private final S3Uploader s3Uploader;

    // @Transactional 없음 - S3와 DB 작업 순서를 여기서 조율
    public ProjectResponse createProject(ProjectCreateRequest request, MultipartFile image) {
        // 1. [트랜잭션 밖] 이미지가 존재하면 S3에 업로드 후 imageUrl 반환, 없으면 null
        //    - 업로드 실패 시 예외를 던져 이후 로직 진행 차단
        // 2. [트랜잭션 안] projectService.createProject(request, imageUrl) 호출하여 DB 저장
        //    - DB 저장 실패 시 예외 발생 → catch에서 S3에 업로드된 이미지 보상 삭제 후 예외 rethrow
        // 3. ProjectResponse 반환
        throw new UnsupportedOperationException("구현 예정");
    }

    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request, MultipartFile image) {
        // 1. [트랜잭션 밖] 새 이미지가 존재하면 S3에 새 이미지 업로드 후 newImageUrl 반환
        //    - 업로드 실패 시 예외를 던져 이후 로직 진행 차단 (기존 이미지 유지)
        // 2. [트랜잭션 안] projectService.updateProject(projectId, request, newImageUrl) 호출하여 DB 수정
        //    - DB 수정 실패 시 예외 발생 → catch에서 새로 업로드한 S3 이미지 보상 삭제 후 예외 rethrow
        // 3. [트랜잭션 밖] DB 수정 성공 후 기존 S3 이미지 삭제
        //    - 기존 이미지 url은 projectService.getProjectImageUrl()로 미리 조회해둬야 함
        // 4. ProjectResponse 반환
        throw new UnsupportedOperationException("구현 예정");
    }

    public void deleteProject(Long projectId) {
        // 1. [트랜잭션 밖] 삭제할 이미지 url을 projectService.getProjectImageUrl()로 미리 조회
        // 2. [트랜잭션 안] projectService.deleteProject(projectId) 호출하여 DB 삭제
        //    - DB 삭제 실패 시 예외 발생 → S3 삭제 진행하지 않음 (이미지 보존)
        // 3. [트랜잭션 밖] DB 삭제 성공 후 S3 이미지 삭제
        throw new UnsupportedOperationException("구현 예정");
    }
}
