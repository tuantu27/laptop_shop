package com.example.laptop_shop.entity;

import com.example.laptop_shop.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private String ram;
    private String hardDrive;
    private String screen;
    private String photo;
    private String cpu;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand ;


    @OneToMany(mappedBy = "productEntity")
    private List<BillDetailEntity> orderDetailList;

    public ProductDTO toDto(ProductEntity product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCpu(product.getCpu());
        productDTO.setPhoto(product.getPhoto());
        productDTO.setRam(product.getRam());
        productDTO.setScreen(product.getScreen());
        productDTO.setHardDrive(product.getHardDrive());
        productDTO.setBrandDTO(product.getBrand().toDto(product.getBrand()));
        return productDTO;
    }

}
