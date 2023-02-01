package com.example.laptop_shop.dto;

import com.example.laptop_shop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long price;
    private String ram;
    private String hardDrive;
    private String screen;
    private String photo;
    private String cpu;
    private Long brandId;
    private BrandDTO brandDTO;
    private Integer quantity;
    private Integer totalPages;
    private Long totalElements;
    private int currentPage;
    private Long totalProduct;
    public ProductDTO(Long id, String name, String photo, Long price,Long totalProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.totalProduct = totalProduct;
    }
    public ProductDTO(Long id, String name, String photo, Long price ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
    }
    public static ProductDTO toDto(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setPhoto(productEntity.getPhoto());

        return productDTO;

    };
}
