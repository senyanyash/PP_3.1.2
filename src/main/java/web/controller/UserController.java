package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user) {
        return "/new";
    }
    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping()
    public String allUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "/allUsers";
    }


    @GetMapping("/{id}/edit")
    public String getEditUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.removeUser(user);
        return "redirect:/users";
    }
}

