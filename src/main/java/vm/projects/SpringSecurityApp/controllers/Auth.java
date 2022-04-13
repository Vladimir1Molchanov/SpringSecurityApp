package vm.projects.SpringSecurityApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.model.User;
import vm.projects.SpringSecurityApp.service.UserService;

import java.util.Set;

@Controller
public class Auth {

    public Auth(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;
    private Authentication authentication;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute("username") String username, Model model) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            return "redirect:admin/";
        } else {
            return "redirect:user/" + username;
        }
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/reg")
    public String newUserForm() {
        return "user-create";
    }

    @PostMapping("/reg")
    public String newUser(User user) {
        userService.saveUser(user);
        return "redirect:login";
    }
}
