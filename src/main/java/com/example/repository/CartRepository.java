package com.example.repository;

import com.example.entity.Cart;
import com.example.entity.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartProductId> {

    @Query("select c from Cart c where c.userCart.userId = :userId order by c.createdTime desc ")
    List<Cart> getAllCartByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "update Cart set quantity = :amount where userCart.userId = :userId and " +
            "product.productID = :productId")
    void updateQuantity(@Param("userId") Long userId,
                        @Param("productId") Long productId,
                        @Param("amount") Integer amount);

}
