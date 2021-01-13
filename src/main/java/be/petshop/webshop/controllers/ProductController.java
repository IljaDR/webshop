package be.petshop.webshop.controllers;

import be.petshop.webshop.models.Product;
import be.petshop.webshop.models.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    ProductDAO dao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAll(){
        return dao.findAll();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(){
        return "pong";
    }
}
