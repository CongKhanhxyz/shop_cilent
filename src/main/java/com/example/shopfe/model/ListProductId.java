package com.example.shopfe.model;

import lombok.Data;

import java.util.List;

@Data
public class ListProductId {
    private List<Long> listProductIds;
    private String name;
}
