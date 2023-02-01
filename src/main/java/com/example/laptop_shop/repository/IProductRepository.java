package com.example.laptop_shop.repository;

import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.entity.BillDetailEntity;
import com.example.laptop_shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findAllByOrderByPriceDesc();
    List<ProductEntity> findProductEntitiesByNameContainingIgnoreCase(String name);

    List<ProductEntity> findAllByOrderByPriceAsc();
    List<ProductEntity> findByPriceBetween(Long from , Long to);



    //get top product in order
//    @Query(value = "SELECT  new com.example.laptop_shop.dto.ProductDTO(p.id, p.name,p.photo,p.price,count(b.productEntity.id)) FROM ProductEntity p inner join BillDetailEntity b on p.id = b.productEntity.id group by b.productEntity.id order by total DESC ")
//    List<ProductDTO> getTopProduct();




}
