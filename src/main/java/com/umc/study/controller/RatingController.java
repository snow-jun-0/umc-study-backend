package com.umc.study.controller;

import com.umc.study.dto.RatingResponse;
import com.umc.study.service.RatingService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class RatingController {

    private final RatingService ratingService;

    // page, size 는 선택이에요. 안 보내면 전체 목록이 나와요.
    @GetMapping("/{memberId}/ratings")
    public List<RatingResponse> getRatings(
            @PathVariable Long memberId,
            @RequestParam(required = false) @Min(value = 0, message = "page는 0 이상이어야 해요.") Integer page,
            @RequestParam(required = false) @Min(value = 1, message = "size는 1 이상이어야 해요.") Integer size) {
        return ratingService.getRatingsByMember(memberId, page, size);
    }
}
