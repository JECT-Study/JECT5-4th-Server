package com.sossbar.projects.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PublicProjectResponse {

    private Long projectId;
    private String projectName;
    private String host;
    private LocalDateTime startDate;
    private String projectImage;
}
