package com.example.laptop_shop.repository;

import com.example.laptop_shop.dto.TopProductDTO;
import com.example.laptop_shop.entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetailEntity,Long> {

    @Query(value = "SELECT new com.example.laptop_shop.dto.TopProductDTO(b.productEntity.id, b.productEntity.name,b.productEntity.price,b.productEntity.photo, count(b) )  FROM BillDetailEntity b  group by b.productEntity.id order by count(b) DESC ")
    List<TopProductDTO> getTopProduct();
}


