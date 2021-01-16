package be.petshop.webshop.controllers;

import be.petshop.webshop.daos.CategoryDAO;
import be.petshop.webshop.models.Category;
import be.petshop.webshop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    CategoryDAO dao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAll(){
        return dao.findAll();
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public Category getByID(@RequestParam(value = "category") int categoryID){
        return dao.getByCategoryID(categoryID);
    }
}
