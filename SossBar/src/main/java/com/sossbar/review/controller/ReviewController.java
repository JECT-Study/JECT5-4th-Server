package com.sossbar.review.controller;

import com.sossbar.global.common.code.SuccessCode;
import com.sossbar.global.common.template.ApiResTemplate;
import com.sossbar.review.dto.request.ReviewCreateReqDto;
import com.sossbar.review.service.ReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("api/v1/reviews")
    public ApiResTemplate createReview(@RequestBody ReviewCreateReqDto reviewCreateReqDto) {
        reviewService.createReview(reviewCreateReqDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.SUCCESS);
    }
}