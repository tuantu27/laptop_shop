package com.example.laptop_shop.repository;

import com.example.laptop_shop.entity.BrandEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity,Long> {
}
