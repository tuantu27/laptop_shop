package com.example.laptop_shop.service;

import com.example.laptop_shop.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAll();
    List<ProductDTO> getListProductByListId(List<Long> ids);
    ProductDTO getProductById(Long id);
    List<ProductDTO> searchByName(String name);

    void updateProduct(ProductDTO productDTO);
    void saveNewProduct(ProductDTO productDTO);
    void deleteProduct(Long id);

    List<ProductDTO> sortProductAsc();
    List<ProductDTO> sortProductDesc();
    List<ProductDTO> filterByPrice(Long from,Long to);
//    List<ProductDTO> pagingProduct(int pageNo, int pageSize);
    Page<ProductDTO> pagingProduct(int pageNo, int pageSize);

    // get top product
//    List<ProductDTO> getTopProduct();

}
