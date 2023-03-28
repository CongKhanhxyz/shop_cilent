package com.example.service;


import com.example.DTO.LikeReviewDto;
import com.example.DTO.ReviewDto;
import com.example.entity.UserReviewLike;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IReviewService {

    List<ReviewDto> getAllByProductId(Long productId, int currentPage);
    List<ReviewDto> getAllByStar(int currentPage, int star);
    ReviewDto saveReview(ReviewDto reviewDto);
    void deleteReview(Long reviewID);
    void updateReview(Long reviewId, ReviewDto reviewDto);
    void updateReviewByOtherUser(Long reviewId);
    List<Integer> getCountOfEachRank(Long productId);

    List<LikeReviewDto> like(@RequestBody LikeReviewDto likeReviewDto);

}
