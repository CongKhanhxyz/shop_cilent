package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ListProductId {
    private Long userId;
    private List<Long> listProductIds;
    private String name;
}
