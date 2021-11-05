package com.example.demo.Controllers;

import com.example.demo.Model.userModel;
import com.example.demo.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Optional;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    userRepository userRepository;

    @GetMapping("/")
    public String homePage(Principal principal, Model model){
        try{
            model.addAttribute("username",principal.getName());
        }catch (NullPointerException e){
            model.addAttribute("username","No user");
        }
        return "home.html";
    }


//    @GetMapping("/profile")
//    public String profile(@RequestParam Integer id , Model model){
//        Optional<userModel> user =  userRepository.findById(id);
//        model.addAttribute("username", user.get().getUsername());
////        model.addAttribute()
//        model.addAttribute("firstName", user.get().getFirstName());
//        model.addAttribute("lastName", user.get().getLastName());
//        model.addAttribute("dateOfBirth", user.get().getDateOfBirth());
//        model.addAttribute("bio", user.get().getBio());
//        return "profile.html";
//    }


}
