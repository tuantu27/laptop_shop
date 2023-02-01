package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.BillDTO;
import com.example.laptop_shop.entity.BillEntity;
import com.example.laptop_shop.entity.CustomerEntity;
import com.example.laptop_shop.repository.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService{
    @Autowired
    IBillRepository iBillRepository;

    @Override
    public Long saveBill(BillDTO orderDTO) {
        BillEntity orderEntity = new BillEntity();
        CustomerEntity customer = new CustomerEntity();
        orderEntity.setAddress(orderDTO.getAddress());
        orderEntity.setEmail(orderDTO.getEmail());
        orderEntity.setTotal(orderDTO.getTotal());
        orderEntity.setPhoneNumber(orderDTO.getPhoneNumber());
        orderEntity.setCustomer(customer);
        BillEntity entity = iBillRepository.save(orderEntity);
        return entity.getId();
    }

    @Override
    public BillDTO getBillById(Long id) {
        Optional<BillEntity> orderEntity = iBillRepository.findById(id);
        BillDTO orderDTO = new BillDTO();
        orderDTO.setId(orderEntity.get().getId());
        orderDTO.setAddress(orderEntity.get().getAddress());
        orderDTO.setEmail(orderEntity.get().getEmail());
        orderDTO.setTotal(orderEntity.get().getTotal());
        orderDTO.setPhoneNumber(orderEntity.get().getPhoneNumber());
        return orderDTO;
    }
}
