package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByProductNameContaining(String name, Pageable pageable);

    @Query("SELECT MAX(productID) FROM Product ")
    Long getLastId();

    @Query("select p from Product p where p.category.categoryName = :categoryName")
    List<Product> getProductsByCategoryName(String categoryName);

    List<Product> getProductsByCategory_CategoryName(String name);
}
