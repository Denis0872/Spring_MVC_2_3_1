package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.service.UserService;
import org.springframework.web.bind.annotation.*;
import web.model.User;


@Controller

public class UserController {

        private  UserService userService;
    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
//

    @GetMapping("edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }


    @PatchMapping("edit")
    public String update2(@ModelAttribute("users") User user){
            this.userService.updateUser(user);
        return "redirect:/";
    }


    @GetMapping("/create")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/create")
    public String addUser2(@ModelAttribute("user") User user) {
            userService.addUser(user);
            return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);
        return "redirect:/";
    }

}