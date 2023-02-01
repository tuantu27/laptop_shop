package com.example.laptop_shop.controller;

import com.example.laptop_shop.config.MvcConfig;
import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBrandService;
import com.example.laptop_shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IBrandService iBrandService;

    @Autowired
    MvcConfig mvcConfig;

    @GetMapping(value = "/product")
    public String manageProduct(Model model){
        List<ProductDTO> listpro = iProductService.getAll();
        model.addAttribute("lstProduct",listpro);
        return "admin_page";
    }
    @GetMapping(value = "/editProduct/{id}")
    public String editProduct(Model model, @PathVariable("id")Long id){
        ProductDTO productDTO = iProductService.getProductById(id);
        List<BrandDTO> lstBrand = iBrandService.getAll();
        model.addAttribute("lstBrand",lstBrand);
        model.addAttribute("productDto",productDTO);
        return "edit_Product_page";

    }

    @PostMapping(value = "/updateProduct")
    public String updateProduct(@ModelAttribute("productDto")ProductDTO productDTO, @RequestParam("newImage") MultipartFile file){
        String nameFile = mvcConfig.uploadImages(file);
        productDTO.setPhoto(nameFile);
        iProductService.updateProduct(productDTO);
        return "redirect:/admin";
    }
    @GetMapping(value = "/addProduct")
    public String addProduct(Model model){
        ProductDTO productDTO = new ProductDTO();
        List<BrandDTO> lstBrand = iBrandService.getAll();
        model.addAttribute("lstBrand",lstBrand);
        model.addAttribute("productDto",productDTO);
        return "add_product_page";
    }

    @PostMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("productDto")ProductDTO productDTO,@RequestParam("newImage")MultipartFile file){
        String nameFile = mvcConfig.uploadImages(file);
        productDTO.setPhoto(nameFile);
        iProductService.saveNewProduct(productDTO);
        return "redirect:/admin";
    }

    @GetMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<List<ProductDTO>> deleteProduct(@PathVariable("id")Long id){
        iProductService.deleteProduct(id);
        List<ProductDTO> productDTOS = iProductService.getAll();
        return new ResponseEntity<List<ProductDTO>>(productDTOS, HttpStatus.OK);
    }
}
