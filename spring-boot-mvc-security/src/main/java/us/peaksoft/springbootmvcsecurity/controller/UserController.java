package us.peaksoft.springbootmvcsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.springbootmvcsecurity.model.Role;
import us.peaksoft.springbootmvcsecurity.model.User;
import us.peaksoft.springbootmvcsecurity.service.RoleService;
import us.peaksoft.springbootmvcsecurity.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Controller
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to our website");
        return "home";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-page";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-page";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "sign-up";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user, Model model,
                           @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "info";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, Model model,
                             @RequestParam Map<String, String> form) {
        List<String> roles = roleService.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.update(user);
        model.addAttribute("users", userService.getAllUsers());
        return "admin-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        userService.delete(userService.getById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "admin-page";
    }
}