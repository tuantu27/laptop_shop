package com.example.laptop_shop.api;

import com.example.laptop_shop.dto.ItemDTO;
import com.example.laptop_shop.service.IProductService;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestfulAPI {
    @Autowired
    private IProductService iProductService;
    @Autowired
    IBrandService iBrandService;

    @GetMapping(value = "/filterByPrice/{from}/{to}")
    public ResponseEntity<List<ProductDTO>> filterByPrice(@PathVariable("from")Long from, @PathVariable("to")Long to){
        List<ProductDTO> list = iProductService.filterByPrice(from,to);
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/sortProductByPrice/{value}" )
    public ResponseEntity<List<ProductDTO>> sortProductByPrice(@PathVariable("value") String content){
        try {
            List<ProductDTO> list = new ArrayList<>();
            if (content.equalsIgnoreCase("LowToHigh")) {
                list = iProductService.sortProductAsc();
            } else if (content.equalsIgnoreCase("HighToLow")) {
                list = iProductService.sortProductDesc();
            }
            return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
        }

    }
//    @GetMapping(value = "/sortProductDesc")
//    public ResponseEntity<List<ProductDTO>> sortProductDesc(){
//        List<ProductDTO> list = iProductService.sortProductDesc();
//        return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
//    }
    @GetMapping(value = "/showQuickView/{id}" )
    public ResponseEntity<ProductDTO> showQuickView(@PathVariable("id") Long id){
        ProductDTO productDTO = iProductService.getProductById(id);
        return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/detailCategory/{id}")
    public ResponseEntity<List<ProductDTO>> getDetailCategory(@PathVariable("id")Long id){
        List<Long> listId = iBrandService.getListProductId(id);
        List<ProductDTO> list = iProductService.getListProductByListId(listId);
        return  new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

  }
    @RequestMapping(value = "/searchProduct/{result}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable("result")String result){
        List<ProductDTO> listProductDTO =  iProductService.searchByName(result);
        return ResponseEntity.ok().body(listProductDTO);

    }
    @RequestMapping(value = "/pagingProduct/{pageNo}",method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> findPaginated(@PathVariable("pageNo")int pageNo, Model model){
        int pageSize = 6;
        Page<ProductDTO> page = iProductService.pagingProduct(pageNo,pageSize);
        List<ProductDTO> lstProduct = page.getContent();

//        List<ProductDTO> listpro = iProductService.pagingProduct(pageNo,pageSize);
//        ProductDTO productDTO = listpro.get(1);
//        List<BrandDTO> lstBrand = iBrandService.getAll();
//        model.addAttribute("lstBrand",lstBrand);
//        model.addAttribute("currentPage",pageNo);
//        model.addAttribute("totalPages",productDTO.getTotalPages());
        return new ResponseEntity<List<ProductDTO>>(lstProduct, HttpStatus.OK);
    }

// remove product in cart
@RequestMapping(value = "removeProduct/{id}",method = RequestMethod.GET)
public ResponseEntity<List<ItemDTO>> removeProduct(@PathVariable("id") Long id, HttpSession session){
    List<ItemDTO> lstItem = (List<ItemDTO>) session.getAttribute("cart");
    for(int i = 0 ; i<lstItem.size(); i++)
    {
        if(lstItem.get(i).getProductDTO().getId()== id){
            lstItem.remove(i);
        }
        break;
    }
    session.setAttribute("cart",lstItem);
    return new ResponseEntity<List<ItemDTO>>(lstItem, HttpStatus.OK);
}
}
