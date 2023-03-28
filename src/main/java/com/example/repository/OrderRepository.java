package com.example.repository;

import com.example.DTO.ProductReport;
import com.example.entity.Order;
import com.example.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT MAX(orderId) FROM Order ")
    Long getLastId();

    List<Order> getOrderByUserUserIdAndShipDate(Long userId, LocalDate shipDate);

    List<Order> getOrderByUserUserId(Long userId);
//    List<Order> getOrderByOrderIdAndGro

    List<Order> getOrderByShipDateGreaterThan(LocalDate shipdate);

    @Query("select new com.example.DTO.ProductReport(o.product.productID, p.productName,  sum(o.quantity), sum (o.totalPrice)) from Order o inner join Product p " +
            "on o.product.productID = p.productID where o.createdDate between :startDate and :endDate group by o.product.productID, p.productName order by o.product.productID desc ")
    List<ProductReport> getOrderByCreatedDateLessThanEqualAndCreatedDateGreaterThanEqual
            (@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

//    Query query = em.createNativeQuery("SELECT name,age FROM jedi_table", Jedi.class);
//    @SuppressWarnings("unchecked")
//    List<Jedi> items = (List<Jedi>) query.getResultList();
}
