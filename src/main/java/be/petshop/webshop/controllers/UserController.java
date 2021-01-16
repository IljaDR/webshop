package be.petshop.webshop.controllers;

import be.petshop.webshop.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/products")
public class UserController {
    @Autowired
    UserDAO dao;


}
