package ru.aibolotov.Spring.BootApp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.Spring.BootApp1.dao.UserDao;
import ru.aibolotov.Spring.BootApp1.model.User;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    @Transactional
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public User getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public boolean userIsExist(User user) {
        return userDao.userIsExist(user);
    }

    @Override
    public boolean nameIsExist(String name) {
        return userDao.nameIsExist(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUser(s);
    }
}
