package com.example.repository;

import com.example.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    @Query("select d from District d where d.province.id = :provinceId")
    List<District> getDistrictsByProvinceId(@Param("provinceId") Long provinceId);

}
