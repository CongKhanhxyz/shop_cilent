package com.example.repository;

import com.example.entity.Discount;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("select d from Discount d inner join DiscountProduct dp on d.discountId = dp.discount.discountId where" +
            " dp.product.productID = :productId")
    List<Discount> getDiscountByProductId(@Param("productId") Long productId);

//    @Query("select d from Discount d where d.startDate > :startDate and d.endDate < :endDate")
//    List<Discount> getDiscountBettwen();

    List<Discount> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(OffsetDateTime startDate,
                                                                             OffsetDateTime endDate);
    @Query("SELECT MAX(discountId) FROM Discount ")
    Long getLastId();
}
