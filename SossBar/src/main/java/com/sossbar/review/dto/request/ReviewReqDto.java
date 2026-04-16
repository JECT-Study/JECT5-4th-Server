package com.sossbar.review.dto.request;

import com.sossbar.review.entity.Review;
import com.sossbar.user.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;


// 리뷰 + 태그 DTO
public record ReviewReqDto(
    @NotBlank
    @Size(min = 10)
    String positiveFeedback,

    @NotBlank
    @Size(min = 10)
    String negativeFeedback,

    @NotNull
    @Positive
    Long reviewerId,

    @NotNull
    @Positive
    Long revieweeId,

    @NotNull
    @Positive
    Long projectId,

    List<Long> tagIds
) {
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    public Review toEntity() {
        return Review.builder()
                .positiveFeedback(positiveFeedback)
                .negativeFeedback(negativeFeedback)
                .reviewer(userRepository.findById(reviewerId))
                .reviewee(userRepository.findById(revieweeId))
                .project(projectRepository.findById(projectId))
                .build();
    }

    public List<Long> getTagIds() {
        return tagIds;
    }
}