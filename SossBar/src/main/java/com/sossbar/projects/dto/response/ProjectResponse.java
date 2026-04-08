package com.sossbar.projects.dto.response;

import com.sossbar.projects.enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectResponse {

    private Long projectId;
    private String projectName;
    private String host;
    private String startDate;
    private String endDate;
    private String projectLink;
    private String projectImage;
    private ProjectStatus projectStatus;
}
