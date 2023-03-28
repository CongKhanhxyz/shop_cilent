package com.example.DTO;

import lombok.Data;

import java.util.List;

@Data
public class SizeDto {

    private String name;
    private int quantity;
    private List<ColorDto> colors;
}
