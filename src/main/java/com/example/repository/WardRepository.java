package com.example.repository;

import com.example.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> getWardByDistrictId(Long districtId);
    Optional<Ward> getWardByName(String wardName);
}
