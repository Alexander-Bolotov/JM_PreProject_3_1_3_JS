package ru.aibolotov.Spring.BootApp1.dao;

import ru.aibolotov.Spring.BootApp1.model.User;


import java.util.List;

public interface UserDao {

    List<User> getListUsers();

    User addUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUser(String name);

    boolean userIsExist(User user);

    boolean nameIsExist(String name);

}
