package com.example.repository;

import com.example.entity.Color;
import com.example.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Size getSizeByName(String name);

    @Query("select c from Color c where c.size.id = :sizeId")
    Optional<List<Color>> getColorBySize(Long sizeId);

    @Query("select sum(c.quantity) from Color c where c.size.id = :sizeId")
    int countBySizeId(Long sizeId);
}
