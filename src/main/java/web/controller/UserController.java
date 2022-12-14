package web.controller;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserController {

        private  UserService userService;


    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping("edit/{id}")
    public String updateUserGet(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }


    @PatchMapping("edit")
    public String updateUserPost( User user){
            this.userService.updateUser(user);
        return "redirect:/";
    }


    @GetMapping("/create")
    public String addUserGet(User user) {
        return "create";
    }

    @PostMapping("/create")
    public String addUserPost(@ModelAttribute("user") User user) {
            userService.addUser(user);
            return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);
        return "redirect:/";
    }

}