package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BillDTO;

public interface IBillService {
    Long saveBill(BillDTO orderDTO);
    BillDTO getBillById(Long id);
}

