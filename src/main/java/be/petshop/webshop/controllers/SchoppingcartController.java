package be.petshop.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SchoppingcartController {
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(@RequestParam(value = "id", required = false) String productID, Principal principal, ModelMap modelMap){
        if(productID != null){
            // add item to cart

        }
        return "temp";
    }
}
