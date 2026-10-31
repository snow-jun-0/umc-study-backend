package com.umc.study.repository;

import com.umc.study.domain.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMember_MemberIdOrderByRatingIdDesc(Long memberId);

    List<Rating> findByMember_MemberIdOrderByRatingIdDesc(Long memberId, Pageable pageable);
}
