package com.sossbar.projects.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectUpdateRequest {

    private String projectName;  // 프로젝트명 (선택적 수정)
    private String host;         // 주최사 (선택적 수정)
    private String startDate;    // 프로젝트 시작 날짜 (선택적 수정)
    private String endDate;      // 프로젝트 종료 날짜 (선택적 수정)

    // 이미지는 MultipartFile로 별도 수신 (Controller의 @RequestPart("image") 참고)
}
