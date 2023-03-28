package com.example.repository;

import com.example.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.order.product.productID = :productId")
    List<Review> getAllByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("select count(r.reviewID) from Review r where r.start = :rank and r.order.product.productID = :productId")
    int countStarOfEachRank(@Param("rank") int rank, @Param("productId") Long productId);

    @Query("select r from Review r where r.start = :rank")
    List<Review>  reviewOfStar(@Param("rank") int rank, Pageable pageable);

    @Query("SELECT MAX(reviewID) FROM Review ")
    Long getLastId();
}
