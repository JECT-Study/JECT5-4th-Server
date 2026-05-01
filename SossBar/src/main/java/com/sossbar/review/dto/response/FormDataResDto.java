package com.sossbar.review.dto.response;

import com.sossbar.spectrumaxis.dto.response.SpectrumAxisResDto;
import com.sossbar.tag.dto.response.TagResDto;

import java.util.List;

public record FormDataResDto (
    List<TagResDto> tagResDtos,
    List<SpectrumAxisResDto> spectrumAxisResDtos
) {
}
