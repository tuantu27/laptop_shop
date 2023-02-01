package com.example.laptop_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private int quantity;
    private ProductDTO productDTO;
    private Long original_price;
    private Long price;
}
