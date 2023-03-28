package com.example.DTO;

import lombok.Data;

@Data
public class LikeReviewDto {
    private Long reviewId;
    private Long userId;
    private int isLike;
    private int totalLike;
}
