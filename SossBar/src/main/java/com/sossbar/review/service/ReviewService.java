package com.sossbar.review.service;

import com.sossbar.review.dto.request.ReviewCreateReqDto;
import com.sossbar.review.dto.request.ReviewReqDto;
import com.sossbar.review.dto.request.SpectrumReqDto;
import com.sossbar.review.entity.Review;
import com.sossbar.review.entity.ReviewSpectrum;
import com.sossbar.review.entity.ReviewTag;
import com.sossbar.review.repository.ReviewRepository;
import com.sossbar.review.repository.ReviewSpectrumRepository;
import com.sossbar.review.repository.ReviewTagRepository;
import com.sossbar.spectrumaxis.entity.SpectrumAxis;
import com.sossbar.spectrumaxis.repository.SpectrumAxisRepository;
import com.sossbar.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private TagRepository tagRepository;
    private SpectrumAxisRepository spectrumAxisRepository;
    private ReviewTagRepository reviewTagRepository;
    private ReviewSpectrumRepository reviewSpectrumRepository;

    @Transactional
    public void createReview(ReviewCreateReqDto reviewCreateReqDto) {
        Review savedReview = reviewRepository.save(reviewCreateReqDto.getReviewReqDto().toEntity());

        for (Long tagId : reviewCreateReqDto.getReviewReqDto().getTagIds()) {
            reviewTagRepository.save(ReviewTag.builder()
                    .review(savedReview)
                    .tag(tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("다음과 같은 태그는 존재하지 않습니다: " + tagId)))
                    .build());
        }

        // 각 스펙트럼 축 당 저장하기...
        List<ReviewSpectrum> reviewSpectrums = reviewCreateReqDto.getSpectrumReqDtos().stream()
                .map(spectrumReqDto -> {
                    SpectrumAxis spectrumAxis = spectrumAxisRepository.findById(spectrumReqDto.getSpectrumAxisId()).orElseThrow(() -> new RuntimeException("다음과 같은 스펙트럼 축은 존재하지 않습니다: " + spectrumReqDto.getSpectrumAxisId()));

                    return ReviewSpectrum.builder()
                            .review(savedReview)
                            .spectrumAxis(spectrumAxis)
                            .strength(spectrumReqDto.getSpectrumStrength())
                            .build();
                        })
                .collect(Collectors.toList());

        reviewSpectrumRepository.saveAll(reviewSpectrums);
    }
}
