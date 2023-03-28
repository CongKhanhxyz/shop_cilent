package com.example.repository;

import com.example.entity.DiscountProduct;
import com.example.entity.DiscountProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiscountProductRepository extends JpaRepository<DiscountProduct, DiscountProductId> {

//    @Modifying
//    @Query(value = "insert into  DiscountProduct (discountId , productID) values  (:discountId, productID) ", nativeQuery = true)
//    void saveId(@Param("productId") Long productId,@Param("discountId") Long discountId);

    @Query("select d from DiscountProduct d where d.discount.discountId = :discountId")
    List<DiscountProduct> findDiscountProductByDiscountDiscountIdD(@Param("discountId") Long discountId);

    List<DiscountProduct> findDiscountProductByProductProductID(Long productId);
}
