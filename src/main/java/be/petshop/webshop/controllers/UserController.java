package be.petshop.webshop.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import be.petshop.webshop.daos.UserDAO;
import be.petshop.webshop.models.Category;
import be.petshop.webshop.models.Product;
import be.petshop.webshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "")
public class UserController {
    @Autowired
    UserDAO dao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @ModelAttribute("newUser")
    public User toSave() {
        return new User();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult) {

        //This does not work for some reason, and I do not know why
        if (bindingResult.hasErrors()) {
            return "register";
        }

        Optional<User> check = dao.findByEmailaddress(user.getEmailaddress());

        //If a user is found with this email, it means they already have an account
        if (check.isPresent()) {
            FieldError fieldError = new FieldError("newUser", "email", "There already exists an account with this email address.");
            bindingResult.addError(fieldError);
            return "register";
        }

        //https://github.com/patrickfav/bcrypt
        //Hash password, add this hash to user, save user
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setHash(bcryptHashString);
        dao.save(user);

        //TODO: login user

        return "redirect:/";
    }
}
