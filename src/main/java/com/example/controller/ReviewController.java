package com.example.controller;


import com.example.DTO.LikeReviewDto;
import com.example.DTO.ReviewDto;
import com.example.repository.ReviewRepository;
import com.example.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/reviews")
@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    IReviewService reviewService;


//    @GetMapping
//    public ResponseEntity<List<ReviewDto>> getAll(@RequestBody ReviewDto reviewDto)
//    {
//
//    }

    @PostMapping()
    public ResponseEntity<?> addComment(@RequestBody ReviewDto reviewDto)
    {
        try {
            reviewService.saveReview(reviewDto);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("lưu thành công", HttpStatus.OK);

    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long reviewId)
    {
        try {
            reviewService.deleteReview(reviewId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("Bạn vừa xóa bình luận", HttpStatus.OK);
    }
//    @PutMapping("{reviewId}")
//    public ResponseEntity<String> updateComment(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto)
//    {
//        try {
//            reviewService.updateReview(reviewId, reviewDto);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
//        }
//        return new ResponseEntity<>("Bạn vừa bình luận", HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll(@RequestParam Long productId,
                                                  @RequestParam Integer currentPage)
    {
        return new ResponseEntity<>(reviewService.getAllByProductId(productId, currentPage), HttpStatus.OK);
    }
    @GetMapping("/{currentPage}/{star}")
    public ResponseEntity<List<ReviewDto>> getAllByStar(@PathVariable Integer currentPage,
                                                  @PathVariable Integer star)
    {
        return new ResponseEntity<>(reviewService.getAllByStar(currentPage, star), HttpStatus.OK);
    }
    @GetMapping("/star")
    public ResponseEntity<?> getCountOfEachRank(@RequestParam Long productId)
    {
        return new ResponseEntity<>(reviewService.getCountOfEachRank(productId), HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<?> getCountReview()
    {
        return new ResponseEntity<>(reviewRepository.getLastId(), HttpStatus.OK);
    }
    @PutMapping("/like")
    public ResponseEntity<?> likeReview(@RequestBody LikeReviewDto likeReviewDto)
    {
        return new ResponseEntity<>(reviewService.like(likeReviewDto), HttpStatus.OK);
    }
}
