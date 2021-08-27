package be.petshop.webshop.controllers;
import be.petshop.webshop.daos.UserDAO;
import be.petshop.webshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping(value = "")
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDAO dao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap modelMap, Principal principal){
        User user = dao.findByUsername(principal.getName()).get();
        modelMap.addAttribute("user", user);
        return "profile";
    }

    @ModelAttribute("newUser")
    public User toSave() {
        return new User();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, HttpServletRequest request) {

        //This does not work for some reason, and I do not know why
        if (bindingResult.hasErrors()) {
            return "register";
        }

        Optional<User> check = dao.findByUsername(user.getUsername());

        //If a user is found with this username, it means they already have an account
        if (check.isPresent()) {
            FieldError fieldError = new FieldError("newUser", "username", "There already exists an account with this username.");
            bindingResult.addError(fieldError);
            return "register";
        }

        //https://github.com/patrickfav/bcrypt
        //Hash password, add this hash to user, save user
//        String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        String bcryptHashString = bCryptPasswordEncoder.encode(user.getPassword());
        user.setHash(bcryptHashString);
        dao.save(user);

        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
