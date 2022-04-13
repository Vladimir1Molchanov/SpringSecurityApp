package vm.projects.SpringSecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vm.projects.SpringSecurityApp.model.User;
import vm.projects.SpringSecurityApp.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("users",
                userService.findAll());

        return "user-list";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:admin/";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",
                userService.findById(id));

        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:admin/";
    }
}
