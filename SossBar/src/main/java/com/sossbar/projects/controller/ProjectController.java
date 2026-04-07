package com.sossbar.projects.controller;

import com.sossbar.global.common.code.SuccessCode;
import com.sossbar.global.common.template.ApiResTemplate;
import com.sossbar.projects.dto.request.ProjectCreateRequest;
import com.sossbar.projects.dto.request.ProjectUpdateRequest;
import com.sossbar.projects.dto.response.ProjectResponse;
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

    private final ProjectService projectService;

    // 1. 새 프로젝트 생성 - POST /api/v1/projects
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResTemplate<ProjectResponse> createProject(
            @RequestPart("request") @Valid ProjectCreateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ApiResTemplate.successResponse(SuccessCode.CREATE_SUCCESS, projectService.createProject(request, image));
    }

    // 2. 프로젝트 개별 조회 - GET /api/v1/projects/{projectId}
    @GetMapping("/{projectId}")
    public ApiResTemplate<ProjectResponse> getProject(
            @PathVariable Long projectId
    ) {
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, projectService.getProject(projectId));
    }

    // 3. 프로젝트 수정 - PATCH /api/v1/projects/{projectId}
    @PatchMapping(value = "/{projectId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResTemplate<ProjectResponse> updateProject(
            @PathVariable Long projectId,
            @RequestPart("request") @Valid ProjectUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ApiResTemplate.successResponse(SuccessCode.UPDATE_SUCCESS, projectService.updateProject(projectId, request, image));
    }

    // 4. 프로젝트 삭제 - DELETE /api/v1/projects/{projectId}
    @DeleteMapping("/{projectId}")
    public ApiResTemplate<Void> deleteProject(
            @PathVariable Long projectId
    ) {
        projectService.deleteProject(projectId);
        return ApiResTemplate.successWithNoContent(SuccessCode.DELETE_SUCCESS);
    }
}
