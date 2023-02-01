package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.BrandDTO;

import java.util.List;

public interface IBrandService {
    List<BrandDTO> getAll();
    List<Long> getListProductId(Long id);

}
