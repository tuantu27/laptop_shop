package com.example.laptop_shop.controller;


import com.example.laptop_shop.dto.ItemDTO;
import com.example.laptop_shop.dto.BillDTO;
import com.example.laptop_shop.dto.BillDetailDTO;
import com.example.laptop_shop.dto.ProductDTO;
import com.example.laptop_shop.service.IBillDetailService;
import com.example.laptop_shop.service.IBillService;
import com.example.laptop_shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private IProductService iProductService;


    @Autowired
    private IBillService iBillService;

    @Autowired
    private IBillDetailService iBillDetailService;




    @GetMapping("/cart")
    public String viewCart(){

        return "cart_page";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id , Model model, HttpSession session){

        ProductDTO productDTO = iProductService.getProductById(id);
        Long price = (Long) (productDTO.getPrice() * 1);
        if(session.getAttribute("cart") == null){

        List<ItemDTO> lstItem = new ArrayList<>();
        lstItem.add(new ItemDTO(1,iProductService.getProductById(id), productDTO.getPrice(), price));

        session.setAttribute("cart",lstItem);


        }else{
            List<ItemDTO> lstItem = (List<ItemDTO>) session.getAttribute("cart");
            int index = isExist(id, lstItem);
            if(index == -1){
                lstItem.add(new ItemDTO(1,iProductService.getProductById(id),productDTO.getPrice(),price));
            }else {
                int quantity = lstItem.get(index).getQuantity() + 1;
                lstItem.get(index).setQuantity(quantity);
                lstItem.get(index).setPrice((long) (quantity*productDTO.getPrice()));
            }
            session.setAttribute("cart",lstItem);
        }
        return "redirect:/shop";

    }
    //check exist
    private int isExist(Long id,List<ItemDTO> cart){
        for (int i = 0; i < cart.size(); i++){
            if(cart.get(i).getProductDTO().getId() == id){
                return i;
            }
        }
        return -1;
    }

    @GetMapping("/updateCart")
    public String updateCart(Model model, @RequestParam("id_Product")Integer[] idPro, @RequestParam("quantities") Integer[]quantities, HttpSession session){
        int i,j;
        List<ItemDTO> lstItem = (List<ItemDTO>) session.getAttribute("cart");
        float total=0;

        for( i=0; i<idPro.length;i++){
            for(j = 0 ; j<lstItem.size(); j++){
                if(new Long(idPro[i]).equals(lstItem.get(j).getProductDTO().getId())){
                    for(int x = 0 ; x<quantities.length;x++){
                        int quantity = quantities[i];
                        lstItem.get(j).setQuantity(quantity);
                        lstItem.get(j).setPrice((long) ((lstItem.get(j).getOriginal_price()*quantity)));

                        break;
                    }
                }

            }
        }

        session.setAttribute("cart",lstItem);
        return"redirect:/cart";
    }

    @GetMapping(value = "/checkout")
    public String checkout(Model model,HttpSession session) {

        BillDTO billDTO = new BillDTO();
        List<ItemDTO> lstItem = (List<ItemDTO>) session.getAttribute("cart");

        long total = 0;

        for (int j = 0; j < lstItem.size(); j++) {

            total += lstItem.get(j).getPrice();
        }
        model.addAttribute("total", total);
        model.addAttribute("billDTO",billDTO);
        return "/checkout_page";
    }

    @GetMapping(value = "/saveorder")
    public String placeorder(Model model, HttpSession session, @ModelAttribute("billDTO") BillDTO billDTO, @RequestParam("total") long total){
        billDTO.setTotal(total);
        Long order_id = iBillService.saveBill(billDTO);
        List<ItemDTO> lstItem = (List<ItemDTO>) session.getAttribute("cart");
        for (ItemDTO itemDTO : lstItem){
            BillDetailDTO billDetailDTO = new BillDetailDTO();
            billDetailDTO.setDetail_number(itemDTO.getQuantity());
            billDetailDTO.setDetail_total_price(itemDTO.getPrice());
            billDetailDTO.setOrderDTO(iBillService.getBillById(order_id));
            billDetailDTO.setProductDTO(iProductService.getProductById(itemDTO.getProductDTO().getId()));
            iBillDetailService.saveBillDetail(billDetailDTO);
        }
        session.removeAttribute("cart");
        return "redirect:/home";
        }
}
