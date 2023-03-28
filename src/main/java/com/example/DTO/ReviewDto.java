package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long reviewID;

    private Long orderId;

    private String reviewText;

    private String urlImage;

    LocalDate createdDate;

    LocalTime createdTime;

    private int star;

    private int totalLike;

    private int isLike;

    private Long userId;

    private String urlImageAvatar;

    private String lastname;
}
