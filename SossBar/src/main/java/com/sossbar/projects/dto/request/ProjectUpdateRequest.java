package com.sossbar.projects.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProjectUpdateRequest {

    private String projectName;  // 프로젝트명 (선택적 수정)
    private String host;         // 주최사 (선택적 수정)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;  // 프로젝트 시작 날짜 (선택적 수정)

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;    // 프로젝트 종료 날짜 (선택적 수정)

    // 이미지는 MultipartFile로 별도 수신 (Controller의 @RequestPart("image") 참고)
}
