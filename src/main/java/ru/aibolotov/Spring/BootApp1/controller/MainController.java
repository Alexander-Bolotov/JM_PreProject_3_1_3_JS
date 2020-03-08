package ru.aibolotov.Spring.BootApp1.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aibolotov.Spring.BootApp1.dao.UserDao;
import ru.aibolotov.Spring.BootApp1.model.Role;
import ru.aibolotov.Spring.BootApp1.model.User;
import ru.aibolotov.Spring.BootApp1.repository.RoleRepository;
import ru.aibolotov.Spring.BootApp1.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserDao userDao;

    @JsonView(User.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsersList() {
        return new ResponseEntity<List<User>>(userDao.getListUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    void editUser(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("roles") Set<Role> role){
        User user = userRepository.getOne(id);
        user.setName(name);
        user.setPassword(password);
        user.setRoles(role);
        userDao.addUser(user);
    }

    @PostMapping(value = "/user")
    void addNewUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("roles") Set<Role> role) {
            User user = new User(name, password, role);
            userRepository.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping(value = "/userRole")
    public String userRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findAllByName(auth.getName());
        Set<Role> roles = user.getRoles();

        Role roleUser = new Role();
        roleUser.setRole("USER");

        for (Role role: roles
             ) {
            if(role.getRole().equals(roleUser.getRole())){
                return "USER";}
        }
        return "ADMIN";
    }
}
