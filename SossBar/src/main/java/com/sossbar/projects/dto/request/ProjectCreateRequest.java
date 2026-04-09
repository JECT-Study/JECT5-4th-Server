package com.sossbar.projects.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProjectCreateRequest {

    @NotBlank(message = "프로젝트명은 필수입니다.")
    private String projectName;      // 프로젝트명

    @NotBlank(message = "주최사는 필수입니다.")
    private String host;             // 주최사

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;     // 프로젝트 시작 날짜 (선택)

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;       // 프로젝트 종료 날짜 (선택)

    // 이미지는 MultipartFile로 별도 수신 (Controller의 @RequestPart("image") 참고)
}
