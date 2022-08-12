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

import javax.validation.Valid;


@Controller

public class UserController {
        User user=new User();
        private  UserService userService;
    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
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
//    @PatchMapping ("/edit")
//    public String update2(User user) {
//            this.userService.updateUser(user);
//            return "redirect:/";
//    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String update2(@ModelAttribute("users") User user){
            this.userService.updateUser(user);

        return "redirect:/";
    }
//    @PostMapping ("/edit")
//    public String update3(@ModelAttribute("user") User user) {
//    this.userService.updateUser(user);
//    return "redirect:/";
//}

    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/new")
    public String addUser2(@ModelAttribute("user") User user) {
            userService.addUser(user);
            return "redirect:/";
    }
//    @PatchMapping("/edit")
//    public String update(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        } else {
//            userService.updateUser(user);
//            return "/";
//        }
//    }
    @RequestMapping("remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);
        return "redirect:/";
    }


}