package com.sossbar.projects.controller;

import com.sossbar.global.common.code.SuccessCode;
import com.sossbar.global.common.template.ApiResTemplate;
import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
import com.sossbar.projects.facade.ProjectFacade;
import com.sossbar.projects.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;
    private final ProjectService projectService;

    // 새 프로젝트 생성 - POST /api/v1/projects
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResTemplate<ProjectResponse> createProject(
            @RequestPart("request") @Valid ProjectCreateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ApiResTemplate.successResponse(SuccessCode.CREATE_SUCCESS, projectFacade.createProject(request, image));
    }

    // 프로젝트 개별 조회 - GET /api/v1/projects/{projectId}
    // S3 작업 없으므로 Facade 거치지 않고 Service 직접 호출
    @GetMapping("/{projectId}")
    public ApiResTemplate<ProjectResponse> getProject(
            @PathVariable Long projectId
    ) {
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, projectService.getProject(projectId));
    }

    // 프로젝트 수정 - PATCH /api/v1/projects/{projectId}
    @PatchMapping(value = "/{projectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResTemplate<ProjectResponse> updateProject(
            @PathVariable Long projectId,
            @RequestPart("request") @Valid ProjectUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ApiResTemplate.successResponse(SuccessCode.UPDATE_SUCCESS, projectFacade.updateProject(projectId, request, image));
    }

    // 프로젝트 삭제 - DELETE /api/v1/projects/{projectId}
    @DeleteMapping("/{projectId}")
    public ApiResTemplate<Void> deleteProject(
            @PathVariable Long projectId
    ) {
        projectFacade.deleteProject(projectId);
        return ApiResTemplate.successWithNoContent(SuccessCode.DELETE_SUCCESS);
    }
}
