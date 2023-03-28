package com.example.repository;

import com.example.entity.UserReviewLike;
import com.example.entity.UserReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewLikeRepository extends JpaRepository<UserReviewLike, UserReviewLikeId> {
}
