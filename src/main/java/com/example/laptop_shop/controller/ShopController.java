package com.example.laptop_shop.controller;

import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IProductService;
import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBrandService;
import com.example.laptop_shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    IProductService iProductService;

    @Autowired
    IBrandService iBrandService;

    @GetMapping(value = "/shop")
    public String viewPage(Model model){
        int pageNo = 1;
        int pageSize = 6;
        Page<ProductDTO> lstProductByPaging = iProductService.pagingProduct(pageNo,pageSize);
//        List<ProductDTO> listpro = iProductService.pagingProduct(1,6);
//        ProductDTO productDTO = listpro.get(1);
        List<BrandDTO> lstBrand = iBrandService.getAll();
        List<ProductDTO> listpro = lstProductByPaging.getContent();
        model.addAttribute("lstBrand",lstBrand);
        model.addAttribute("lstProduct",listpro);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalItems",lstProductByPaging.getTotalElements());
        model.addAttribute("totalPages",lstProductByPaging.getTotalPages());
        return "shop_page";
    }



}
