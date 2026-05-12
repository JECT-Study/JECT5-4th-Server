package com.sossbar.user.service;

import com.sossbar.global.common.code.ErrorCode;
import com.sossbar.global.common.exception.BusinessException;
import com.sossbar.global.config.S3Service;
import com.sossbar.user.dto.request.UserInfoUpdateReqDto;
import com.sossbar.user.dto.response.UserInfoResDto;
import com.sossbar.user.entity.User;
import com.sossbar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final S3Service s3Service;

    // 온보딩 - 사용자 추가 정보 입력 (실명, 한 줄 소개, 프로필 이미지)
    @Transactional
    public UserInfoResDto onboarding(Principal principal, UserInfoUpdateReqDto userInfoUpdateReqDto, MultipartFile profileImage) {
        Long id = Long.parseLong(principal.getName());
        User user = getUserById(id);

        // 이미 온보딩 완료된 경우 예외
        if (user.getUsername() != null) {
            throw new BusinessException(
                    ErrorCode.VALIDATION_ERROR,
                    "이미 온보딩이 완료된 사용자입니다."
            );
        }

        // 아무것도 보내지 않으면 초기 이미지는 null로
        String profileImageUrl = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            profileImageUrl = s3Service.uploadFile(profileImage, "sossbar/profile");
        }

        user.updateUserInfo(userInfoUpdateReqDto, profileImageUrl);

        return UserInfoResDto.from(user);
    }

    // 마이페이지 - 내 계정 정보 조회(실명, 이메일)
    public UserInfoResDto getUserInfo(Principal principal) {
        Long id = Long.parseLong(principal.getName());
        User user = getUserById(id);

        return UserInfoResDto.from(user);
    }

    // entity 찾는 공통 메소드
    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION
                        , ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage() + userId));
    }
}
