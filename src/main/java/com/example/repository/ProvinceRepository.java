package com.example.repository;

import com.example.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province getProvinceByName(String provinceName);
}
//santity
// /