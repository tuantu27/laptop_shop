package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BillDetailDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.dto.TopProductDTO;

import java.util.List;

public interface IBillDetailService {
    void saveBillDetail (BillDetailDTO billDetailDTO);
    List<TopProductDTO> getTopProduct();

}
