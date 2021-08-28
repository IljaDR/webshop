package be.petshop.webshop.controllers;

import be.petshop.webshop.daos.ProductDAO;
import be.petshop.webshop.daos.ShoppingcartDAO;
import be.petshop.webshop.daos.UserDAO;
import be.petshop.webshop.models.Product;
import be.petshop.webshop.models.Shoppingcart;
import be.petshop.webshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SchoppingcartController {
    @Autowired
    ShoppingcartDAO shoppingcartDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ProductDAO productDAO;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(@RequestParam(value = "id", required = false) Integer productID, Principal principal, ModelMap modelMap){
        User user = userDAO.findByUsername(principal.getName()).get();
        List<Shoppingcart> shoppingcartList = shoppingcartDAO.findAllByUserID(user.getUserID());
        double totalPrice = 0;
        Shoppingcart shoppingcart;
        // if this user does not have a shopping cart yet
        if(shoppingcartList.isEmpty()){
            // create shopping cart
            shoppingcart = new Shoppingcart();
            shoppingcart.setUserID(user.getUserID());
        }
        else{
            // get existing shopping cart and calculate the total price
            shoppingcart = shoppingcartList.get(0);
            for (Product product: shoppingcart.getProducts()){
                totalPrice += product.getPrice();
            }
        }

        // if productID is filled, that means the user just added an item to their shoppingcart
        if(productID != null){
            Optional<Product> product = productDAO.findById(productID);
            List<Product> shoppingcartproducts = shoppingcart.getProducts();
            // make a list of products if it doesn't exist yet
            if(shoppingcartproducts == null){
                List<Product> tempProducts = new ArrayList<Product>();
                tempProducts.add(product.get());
                shoppingcart.setProducts(tempProducts);
            }
            else{
                shoppingcartproducts.add(product.get());
                shoppingcart.setProducts(shoppingcartproducts);
            }
            shoppingcartDAO.save(shoppingcart);
            totalPrice+=product.get().getPrice();
        }

        modelMap.addAttribute("products", shoppingcart.getProducts());
        modelMap.addAttribute("price", totalPrice);
        return "cart";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(Principal principal, ModelMap modelMap){
        User user = userDAO.findByUsername(principal.getName()).get();
        List<Shoppingcart> shoppingcartList = shoppingcartDAO.findAllByUserID(user.getUserID());
        double totalPrice = 0;
        // get total price
        if(!shoppingcartList.isEmpty()){
            Shoppingcart shoppingcart = shoppingcartList.get(0);
            for (Product product: shoppingcart.getProducts()){
                totalPrice += product.getPrice();
            }
        }
        modelMap.addAttribute("price", totalPrice);
        return "order";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(Principal principal, ModelMap modelMap){
        // ideally the shopping cart would get archived instead of deleted...
        User user = userDAO.findByUsername(principal.getName()).get();
        shoppingcartDAO.deleteByUserID(user.getUserID());
        return "redirect:/";
    }
}
