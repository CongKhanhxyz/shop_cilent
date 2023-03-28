package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    private String reviewText;

    private String urlImage;

    private LocalDate createdDate;

    private LocalTime createdTime;

    private int start;

    private int totalLike;

    private int isLike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Transient
    public String getPhotosImagePath() {
        if (urlImage == null || reviewID == null) return null;

        return "/imgs/reviews/review" + reviewID+ "/" + urlImage;
    }
}
