package ru.aibolotov.Spring.BootApp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aibolotov.Spring.BootApp1.model.Role;
import ru.aibolotov.Spring.BootApp1.model.User;
import ru.aibolotov.Spring.BootApp1.repository.RoleRepository;
import ru.aibolotov.Spring.BootApp1.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
public class pageController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/index")
    public String index() {
        return "/login";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "/login";
    }

    @RequestMapping(value = "/mainPage")
    public String getMainPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findAllByName(auth.getName());
        Set<Role> userRoles = new HashSet<>(roleRepository.findAll());
        int i = userRepository.findAll().size();
        User saveUser = new User();
        saveUser.setId((long) (i - 1));
        saveUser.setName("");
        saveUser.setPassword("");
        saveUser.setRoles(userRoles);

        model.addAttribute("userAuth", user);
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("saveUser", saveUser);
        model.addAttribute("roles", userRoles);
        return "/mainPage";
    }
}
