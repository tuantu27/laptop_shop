package com.example.laptop_shop.controller;

import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBrandService;
import com.example.laptop_shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private IBrandService iBrandService;
    @Autowired
    IProductService iProductService;

    @GetMapping(value = "/detailCategory/{id}")
    public ResponseEntity<List<ProductDTO>> getDetailCategory(@PathVariable("id")Long id){
    List<Long> listId = iBrandService.getListProductId(id);
    List<ProductDTO> list = iProductService.getListProductByListId(listId);
     return  new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

    }

}
