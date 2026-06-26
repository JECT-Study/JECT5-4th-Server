package com.sossbar.review.dto.response;

import com.sossbar.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

// 다른 사용자 전체 후기 조회 DTO
@Getter
@Builder
public class ReviewPublicResDto extends CommonReviewResDto {
    private String projectName;
    private String host;
    private String feedback;

    public static ReviewPublicResDto from(Review review) {
        ReviewPublicResDto dto = ReviewPublicResDto.builder()
                .projectName(review.getProject().getProjectName())
                .host(review.getProject().getHost())
                .feedback(review.getFeedback())
                .build();
        dto.reviewId = review.getReviewId();
        dto.projectImage = review.getProject().getProjectImage();
        dto.createdAt = review.getCreatedAt();
        return dto;
    }
}
