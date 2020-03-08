package ru.aibolotov.Spring.BootApp1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.aibolotov.Spring.BootApp1.model.User;


import java.util.List;

public interface UserService  extends UserDetailsService {


    List<User> getListUsers();

    void addUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUser(String name);

    boolean userIsExist(User user);

    boolean nameIsExist(String name);
}
