package vm.projects.SpringSecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.model.User;
import vm.projects.SpringSecurityApp.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",
                userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") Long[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin/users";
    }
}
