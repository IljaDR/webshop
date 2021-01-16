package be.petshop.webshop.controllers;

import be.petshop.webshop.daos.CategoryDAO;
import be.petshop.webshop.models.Category;
import be.petshop.webshop.models.Product;
import be.petshop.webshop.daos.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    ProductDAO dao;
    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAll(){
        return dao.findAll();
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getByCategory(@RequestParam(value = "category") int categoryID){
        Category category = categoryDAO.getByCategoryID(categoryID);
        return dao.findByCategory(category);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping(){
        return "pong";
    }
}
