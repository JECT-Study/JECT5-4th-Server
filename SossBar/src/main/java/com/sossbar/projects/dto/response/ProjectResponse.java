package com.sossbar.projects.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectResponse {

    private Long projectId;
    private String projectName;
    private String host;
    private String collaborationImageUrl;
}
