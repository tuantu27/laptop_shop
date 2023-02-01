package com.example.laptop_shop.entity;

import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "brand")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(mappedBy = "brand",cascade = CascadeType.PERSIST)
    private List<ProductEntity> listProduct = new ArrayList<>();
    public BrandDTO toDto(BrandEntity brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        List<ProductDTO> productDTOS = new ArrayList<>();
        brand.getListProduct().stream().forEach(e ->{
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(e.getId());
            productDTO.setName(e.getName());
            productDTO.setPrice(e.getPrice());
            productDTO.setCpu(e.getCpu());
            productDTO.setPhoto(e.getPhoto());
            productDTO.setRam(e.getRam());
            productDTO.setHardDrive(e.getHardDrive());
            productDTOS.add(productDTO);
        });
        brandDTO.setProductDTOList(productDTOS);
        return brandDTO;
    }
}
