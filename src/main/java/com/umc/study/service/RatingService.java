package com.umc.study.service;

import com.umc.study.domain.Rating;
import com.umc.study.dto.RatingResponse;
import com.umc.study.repository.MemberRepository;
import com.umc.study.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MemberRepository memberRepository;

    public List<RatingResponse> getRatingsByMember(Long memberId, Integer page, Integer size) {
        // 없는 유저인데 빈 배열을 주면 "평점이 없는 유저"와 구분이 안 돼요.
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }

        List<Rating> ratings = (page == null || size == null)
                ? ratingRepository.findByMember_MemberIdOrderByRatingIdDesc(memberId)
                : ratingRepository.findByMember_MemberIdOrderByRatingIdDesc(memberId, PageRequest.of(page, size));

        return ratings.stream().map(RatingResponse::from).toList();
    }
}
