package com.example.laptop_shop.controller;

import com.example.laptop_shop.dto.*;
import com.example.laptop_shop.service.*;
import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBrandService;
import com.example.laptop_shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    IProductService iProductService;
    @Autowired
    IBrandService iBrandService;

    @Autowired
    IBillDetailService iBillDetailService;

    @GetMapping(value = "/home")
    public String viewPage(Model model){
        List<ProductDTO> listpro = iProductService.getAll();
        List<BrandDTO> lstBrand = iBrandService.getAll();
        List<TopProductDTO>listTopPro = iBillDetailService.getTopProduct();
        model.addAttribute("lstBrand",lstBrand);
        model.addAttribute("lstProduct",listpro);
        model.addAttribute("lstTopProduct",listTopPro);
        return "home_page";
    }


}
