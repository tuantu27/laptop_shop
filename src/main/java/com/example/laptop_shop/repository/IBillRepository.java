package com.example.laptop_shop.repository;

import com.example.laptop_shop.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends JpaRepository<BillEntity,Long> {

}
