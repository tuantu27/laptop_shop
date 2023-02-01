package com.example.laptop_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDTO {
    private Long id;

    private Long detail_total_price;

    private int detail_number;

    BillDTO orderDTO;
    ProductDTO productDTO;
}
