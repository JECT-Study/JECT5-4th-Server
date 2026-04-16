package com.sossbar.review.dto.response;

import com.sossbar.spectrumaxis.dto.response.SpectrumAxisResDto;
import com.sossbar.tag.dto.response.TagResDto;

import java.util.List;

// 아마 마이페이지에서 리뷰 조회할 때 필요할 듯
public record ReviewResDto (
        String positiveFeedback,
        String negativeFeedback,
        Long revieweeId,
        Long projectId,
        List<TagResDto> tags,
        List<SpectrumAxisResDto> spectrumAxes
) {
}
