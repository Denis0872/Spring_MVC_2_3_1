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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


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

    @GetMapping("/create")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/create")
    public String addUser2(@ModelAttribute("user") User user) {
            userService.addUser(user);
            return "redirect:/";
    }

    @RequestMapping("remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);
        return "redirect:/";
    }
 //   ------------------------------------------------
//    @GetMapping("/userParam")
//    public String paramUser(HttpServletRequest request) {
//        String name =request.getParameter("name");
//        String surname =request.getParameter("surname");
//        System.out.println("hi "+ name+" "+surname);
//        return "userParam";
//    }
//    @GetMapping("/userParam")
//    public String paramUser2(@RequestParam ("name") String name, @RequestParam ("surname") String surname) {
//
//        System.out.println("hi "+ name+" "+surname);
//        return "userParam";
//    }


}