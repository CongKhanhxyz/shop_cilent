package com.example.repository;

import com.example.entity.Discount;
import com.example.entity.Product;
import com.example.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    Size getSizeByName(String name);

    @Query("select s from Size s where s.product.productID = :productId")
    Optional<List<Size>> getSizeByProduct(Long productId);
}
