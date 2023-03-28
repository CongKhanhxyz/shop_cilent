package com.example.service.impl;

import com.example.DTO.LikeReviewDto;
import com.example.DTO.ReviewDto;
import com.example.entity.Order;
import com.example.entity.Review;
import com.example.entity.UserReviewLike;
import com.example.entity.UserReviewLikeId;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.UserReviewLikeRepository;
import com.example.security.repository.UserRepository;
import com.example.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    private static final Integer size = 7;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserReviewLikeRepository userReviewLikeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getAllByProductId(Long productId, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return reviewRepository.getAllByProductId( productId,  pageable)
                .stream().map(review ->
                        {
                            ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
                            reviewDto.setUserId(review.getUser().getUserId());
                            reviewDto.setLastname(review.getUser().getLastname());
                            reviewDto.setUrlImageAvatar(review.getUser().getPhotosImagePath());
                            reviewDto.setUrlImage(review.getPhotosImagePath());
                            reviewDto.setStar(review.getStart());
                            return reviewDto;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByStar(int currentPage, int star) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return reviewRepository.reviewOfStar(star, pageable)
                .stream().map(review ->
                        {
                            ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
                            reviewDto.setUserId(review.getUser().getUserId());
                            reviewDto.setLastname(review.getUser().getLastname());
                            reviewDto.setUrlImageAvatar(review.getUser().getPhotosImagePath());
                            reviewDto.setUrlImage(review.getPhotosImagePath());
                            reviewDto.setStar(review.getStart());
                            return reviewDto;
                        }

                ).collect(Collectors.toList());
    }

    // buy can be review
    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        Optional<Order> orderOptional = orderRepository.findById(reviewDto.getOrderId());
        if (orderOptional.isPresent())
        {
            if (orderOptional.get().getStatus() == 4)
            {
                review.setReviewText(reviewDto.getReviewText());
                review.setCreatedDate(LocalDate.now());
                review.setCreatedTime(LocalTime.now());
                review.setUrlImage(reviewDto.getUrlImage());
                review.setStart(reviewDto.getStar());
//                Product product = orderOptional.get().getProduct();
//                product.setTotalLike(reviewDto.getStar());
//                productRepository.save(product);
                review.setUser(orderOptional.get().getUser());
                review.setOrder(orderOptional.get());
                orderOptional.get().getProduct()
                        .setReviewAmount(orderOptional.get().getProduct().getReviewAmount() + 1);
            }
            else {
                throw new RuntimeException("Chưa mua không được đánh giá");
            }
        }
        else {
            throw new RuntimeException("Không có sản phẩm được mua");
        }
//        ReviewDto reviewDtoReturn =  modelMapper.map(reviewRepository.save(review), ReviewDto.class);
//        reviewDtoReturn.setUrlImageAvatar(review.getUser().getUrlImageAvatar());
//        reviewDtoReturn.setLastname(review.getUser().getLastname());
//        reviewDtoReturn.setUserId(review.getUser().getUserId());
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void updateReview(Long reviewId, ReviewDto reviewDto) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent())
        {
            optionalReview.get().setUrlImage(reviewDto.getUrlImage());
            optionalReview.get().setReviewText(reviewDto.getReviewText());
            optionalReview.get().setCreatedDate(LocalDate.now());
            optionalReview.get().setCreatedTime(LocalTime.now());
            optionalReview.get().setUrlImage(reviewDto.getUrlImage());
            optionalReview.get().setStart(reviewDto.getStar());
            reviewRepository.save(optionalReview.get());
        }
        else {
            throw new RuntimeException("Không có review");
        }
    }

    @Override
    public void updateReviewByOtherUser(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent())
        {
            optionalReview.get().setIsLike(1);
            optionalReview.get().setTotalLike(optionalReview.get().getTotalLike() + 1);
            reviewRepository.save(optionalReview.get());
        }
        else {
            throw new RuntimeException("Không có review");
        }
    }

    @Override
    public List<Integer> getCountOfEachRank(Long productId) {
        List<Integer> countOfEachRank = new ArrayList<>();
        for (int i = 5; i >= 1; i--)
        {
            int count = reviewRepository.countStarOfEachRank(i, productId);
            countOfEachRank.add(count);
        }
        return countOfEachRank;
    }

    @Override
    public List<LikeReviewDto> like(LikeReviewDto likeReviewDto) {
        Optional<Review> optionalReview = reviewRepository.findById(likeReviewDto.getReviewId());
        UserReviewLikeId userReviewLikeId = new UserReviewLikeId(likeReviewDto.getUserId(),
                likeReviewDto.getReviewId());
        Optional<UserReviewLike> userReview = userReviewLikeRepository.findById(userReviewLikeId);
        if (userReview.isEmpty()){
            UserReviewLike userReviewLike = new UserReviewLike();
            userReviewLike.setReview(reviewRepository.getReferenceById(likeReviewDto.getReviewId()));
            userReviewLike.setUser(userRepository.getReferenceById(likeReviewDto.getUserId()));
            userReviewLikeRepository.save(userReviewLike);
            optionalReview.get().setIsLike(1);
            optionalReview.get().setTotalLike(optionalReview.get().getTotalLike() + 1);
        }
        else {

            userReviewLikeRepository.deleteById(userReviewLikeId);
            optionalReview.get().setTotalLike(optionalReview.get().getTotalLike() - 1);
            if (optionalReview.get().getTotalLike() - 1 < 1)
            {
                optionalReview.get().setIsLike(0);
            }
        }
        reviewRepository.save(optionalReview.get());
        likeReviewDto.setIsLike(optionalReview.get().getIsLike());
        likeReviewDto.setTotalLike(optionalReview.get().getTotalLike());
        List<LikeReviewDto> list = new ArrayList<>();
        list.add(likeReviewDto);
        return list;
    }
}
/*
* 100 sao 40 cmt > 2.5
* 100 sao 30 cmt > 3.3 3sao
* 100 :25> 4 sao
* 100 : 22 > 4.5
* */