package be.petshop.webshop.controllers;

import be.petshop.webshop.daos.CategoryDAO;
import be.petshop.webshop.models.Category;
import be.petshop.webshop.models.Product;
import be.petshop.webshop.daos.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class ProductController {
    @Autowired
    ProductDAO dao;
    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping(value = "")
    public String index(@RequestParam(value = "category", required = false) String category, ModelMap modelMap){
        List<Product> products = null;
        List<Category> categories = categoryDAO.findAll();
        //If category's not null, this means a parameter was given and thus a specific category is requested
        if(category != null){
            List<Category> categoryList = new ArrayList<>();
            categoryList.add(categoryDAO.getByCategoryID(Integer.parseInt(category)));
            products = dao.findByCategoriesIn(categoryList);
        }
        else{
            products = dao.findAll();
        }

        modelMap.addAttribute("Products", products);
        modelMap.addAttribute("Categories", categories);
        return "index";
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
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
