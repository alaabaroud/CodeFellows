package com.example.demo.Controllers;

import com.example.demo.Model.Post;
import com.example.demo.Model.userModel;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Set;

public class PostController {

    @Autowired
    userRepository userRepository;

    @Autowired
    PostRepo postRepo;


    @GetMapping("/profile")
    public String UserProfile(Principal principal , Model model) {
        if (userRepository != null) {
            model.addAttribute("userData", principal.getName());
            model.addAttribute("allUserData", userRepository.findByUsername(principal.getName()));
        } else {
            model.addAttribute("userData", "No user");
            model.addAttribute("allUserData", new userModel());
        }
        return "user.html";
    }
@PostMapping("/profile")
public RedirectView addNewPost(Principal principal , @RequestParam String body){
    Post post = new Post(body , userRepository.findByUsername(principal.getName()));
    postRepo.save(post);
    return new RedirectView("/userprofile");
}
    @GetMapping("/allUsers")
    public String getAllUsers(Principal principal,Model model){
        try{
            model.addAttribute("userData",principal.getName());
            model.addAttribute("AllUsers",userRepository.findAll());

            userModel user = userRepository.findByUsername(principal.getName());
            model.addAttribute("userFollow",user.getFollowers());
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "allUsers.html";
    }

    @PostMapping("/follow")
    public RedirectView addFollow(Principal principal, @RequestParam int id){
        userModel user = userRepository.findByUsername(principal.getName());
        userModel toFollow = userRepository.findById(id).get();
        user.getFollowers().add(toFollow);

        userRepository.save(user);
        return new RedirectView("/feed");
    }

    @GetMapping("/feed")
    public String getFollowingInfo(Principal principal, Model model){
        try{
            model.addAttribute("userData",principal.getName());
            userModel user = userRepository.findByUsername(principal.getName());

            Set<userModel> userFollow = user.getFollowers();

            model.addAttribute("Allfollowing",userFollow);
        }catch (NullPointerException e){
            model.addAttribute("userData","");
        }
        return "feed.html";
    }

}
