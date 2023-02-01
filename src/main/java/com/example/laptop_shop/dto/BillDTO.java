package com.example.laptop_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {

    private String email;
    private String address;

    private Long phoneNumber;
    private Long total;
    private Long id;
//    List<OrderDetailDTO> lstOrderDetail = new ArrayList<>();

}
