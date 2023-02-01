package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BillDetailDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.dto.TopProductDTO;
import com.example.laptop_shop.entity.BillDetailEntity;
import com.example.laptop_shop.entity.ProductEntity;
import com.example.laptop_shop.repository.IBillDetailRepository;
import com.example.laptop_shop.repository.IBillRepository;
import com.example.laptop_shop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BillDetailService implements IBillDetailService{

    @Autowired
    IBillDetailRepository iBillDetailRepository;

    @Autowired
    IBillRepository iBillRepository;


    @Autowired
    IProductRepository iProductRepository;

    @Override
    public void saveBillDetail(BillDetailDTO billDetailDTO) {
        BillDetailEntity orderDetailEntity = new BillDetailEntity();
        orderDetailEntity.setDetail_number(billDetailDTO.getDetail_number());
        orderDetailEntity.setDetail_total_price(billDetailDTO.getDetail_total_price());
        orderDetailEntity.setOrderEntity(iBillRepository.findById(billDetailDTO.getOrderDTO().getId()).get());
        orderDetailEntity.setProductEntity(iProductRepository.findById(billDetailDTO.getProductDTO().getId()).get());
        iBillDetailRepository.save(orderDetailEntity);
    }

    @Override
    public List<TopProductDTO> getTopProduct() {
        List<TopProductDTO> entityList = iBillDetailRepository.getTopProduct().subList(0,4);
//        List<TopProductDTO> dtoList = new ArrayList<>();
//        for (int i = 0 ; i<=entityList.size();i++) {
//            if(i==1){
//                break;
//            }else {
//                BillDetailDTO billDetailDTO = new BillDetailDTO();
//                billDetailDTO.setId(entityList.get(i).getId());
//                billDetailDTO.setProductDTO(iProductRepository.findById(entityList.get(i).getId()).get().toDto(iProductRepository.findById(entityList.get(i).getId()).get()));
//
//
//                dtoList.add(billDetailDTO);
//            }
//
//        }
        return entityList;
    }


}
