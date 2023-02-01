package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.repository.IBrandRepository;
import com.example.laptop_shop.dto.BrandDTO;
import com.example.laptop_shop.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService{

    @Autowired
    IBrandRepository iBrandRepository;

    @Override
    public List<BrandDTO> getAll() {
        List<BrandEntity> entityList = iBrandRepository.findAll();
        List<BrandDTO> dtoList = new ArrayList<>();
        entityList.stream().forEach(e->{
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(e.getId());
            brandDTO.setName(e.getName());
            dtoList.add(brandDTO);
        });
        return dtoList;
    }

    @Override
    public List<Long> getListProductId(Long id) {
        Optional<BrandEntity> brandEntity = iBrandRepository.findById(id);
        List<Long> listId = new ArrayList<>();
        brandEntity.get().getListProduct().stream().forEach(e->listId.add(e.getId()));
        return listId;
    }

}
