package com.example.demo.Controllers;

import com.example.demo.Model.userModel;
import com.example.demo.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller

public class userController {
    @Autowired
    userRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String getSignUp() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage(Principal principal, Model model){
        try{
            model.addAttribute("userData",principal.getName());
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                               @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "dateOfBirth") String dateOfBirth, @RequestParam(value = "bio") String bio) {
        userModel newUser = new userModel(username, bCryptPasswordEncoder
                .encode(password), firstName, lastName, dateOfBirth, bio);
        userRepository.save(newUser);
        return new RedirectView("/login");
    }

}
