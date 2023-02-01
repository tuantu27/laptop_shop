package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.entity.BillDetailEntity;
import com.example.laptop_shop.entity.ProductEntity;
import com.example.laptop_shop.repository.IBillDetailRepository;
import com.example.laptop_shop.repository.IBrandRepository;
import com.example.laptop_shop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    IBrandRepository iBrandRepository;

    @Autowired
    IBillDetailRepository iBillDetailRepository;

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> entityList = iProductRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setPhoto(entity.getPhoto());

            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public List<ProductDTO> getListProductByListId(List<Long> ids) {
        List<ProductEntity> listpro = iProductRepository.findAllById(ids);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity productEntity: listpro) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setPhoto(productEntity.getPhoto());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductById(Long id) {
       ProductEntity productEntity = iProductRepository.findById(id).get();
       ProductDTO productDTO = productEntity.toDto(productEntity);
        return productDTO;
    }

    @Override
    public List<ProductDTO> searchByName(String name) {
        List<ProductEntity> productEntities =iProductRepository.findProductEntitiesByNameContainingIgnoreCase(name);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity product:productEntities) {
            ProductDTO productDTO = product.toDto(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = iProductRepository.findById(productDTO.getId()).get();
        productEntity.setCpu(productDTO.getCpu());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setPhoto(productDTO.getPhoto());
        productEntity.setHardDrive(productDTO.getHardDrive());
        productEntity.setBrand(iBrandRepository.findById(productDTO.getBrandId()).get());
        productEntity.setRam(productDTO.getRam());
        productEntity.setScreen(productDTO.getScreen());
        iProductRepository.save(productEntity);
    }

    @Override
    public void saveNewProduct(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setCpu(productDTO.getCpu());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setName(productDTO.getName());
        productEntity.setPhoto(productDTO.getPhoto());
        productEntity.setHardDrive(productDTO.getHardDrive());
        productEntity.setBrand(iBrandRepository.findById(productDTO.getBrandId()).get());
        productEntity.setRam(productDTO.getRam());
        productEntity.setScreen(productDTO.getScreen());
        iProductRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> sortProductAsc() {
        List<ProductEntity> entityList =  iProductRepository.findAllByOrderByPriceAsc();

        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setPhoto(entity.getPhoto());

            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public List<ProductDTO> sortProductDesc() {
        List<ProductEntity> entityList =  iProductRepository.findAllByOrderByPriceDesc();

        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setPhoto(entity.getPhoto());

            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public List<ProductDTO> filterByPrice(Long from, Long to) {
        List<ProductEntity> entityList =  iProductRepository.findByPriceBetween(from,to);

        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(entity.getId());
            productDTO.setName(entity.getName());
            productDTO.setPrice(entity.getPrice());
            productDTO.setPhoto(entity.getPhoto());

            dtoList.add(productDTO);
        }
        return dtoList;
    }

    @Override
    public Page<ProductDTO> pagingProduct(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<ProductDTO> lstProductPage = iProductRepository.findAll(pageable).map(ProductDTO::toDto);
        return lstProductPage;
    }
//
//    @Override
//    public List<ProductDTO> getTopProduct() {
//        List<BillDetailEntity> entityList = iBillDetailRepository.getTopProduct();
//        List<ProductDTO> dtoList = new ArrayList<>();
//        for (int i = 0 ; i<=entityList.size();i++) {
//            if(i==1){
//                break;
//            }else {
//                ProductDTO productDTO = new ProductDTO();
//                productDTO.setId(entityList.get(i).getProductEntity().getId());
//                productDTO.setName(entityList.get(i).getProductEntity().getName());
//                productDTO.setPrice(entityList.get(i).getProductEntity().getPrice());
//                productDTO.setPhoto(entityList.get(i).getProductEntity().getPhoto());
//
//                dtoList.add(productDTO);
//            }
//
//        }
//        return dtoList;
//    }
//paging
//    @Override
//    public List<ProductDTO> pagingProduct(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
//        Page<ProductEntity> pageResult=iProductRepository.findAll(pageable);
//
//        List<ProductEntity> entityList =  pageResult.toList();
//
//        List<ProductDTO> dtoList = new ArrayList<>();
//        for (ProductEntity entity : entityList) {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(entity.getId());
//            productDTO.setName(entity.getName());
//            productDTO.setCurrentPage(pageNo);
//            productDTO.setTotalPages(pageResult.getTotalPages());
//            productDTO.setTotalElements(pageResult.getTotalElements());
//            productDTO.setPrice(entity.getPrice());
//            productDTO.setPhoto(entity.getPhoto());
//
//            dtoList.add(productDTO);
//        }
//        return dtoList;
//    }



}
