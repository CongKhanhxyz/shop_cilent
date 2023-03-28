package com.example.repository;

import com.example.DTO.AddressDto;
import com.example.entity.Address;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.user.userId = :userId")
    Address getAddressByUserId(Long userId);

    @Query("select a from Address a where a.user.userId = :userId and a.defaultAddress = 1")
    Address getWardIdByUserIdAndIsDefault(@Param("userId") Long userId);

    @Query("select a from Address a where a.addressId = :addressId")
    Address getWardIdByUserId(@Param("addressId") Long addressId);

    @Query("select a from Address a where a.user.userId = :userId order by a.defaultAddress desc ")
    List<Address> getAllAddressByUserId(@Param("userId") Long userId);
}
