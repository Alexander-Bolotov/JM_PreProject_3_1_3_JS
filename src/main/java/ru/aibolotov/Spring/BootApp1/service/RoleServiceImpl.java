package ru.aibolotov.Spring.BootApp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aibolotov.Spring.BootApp1.dao.RolesDao;
import ru.aibolotov.Spring.BootApp1.model.Role;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolesDao rolesDao;
    @Override
    public List<Role> getListRoles() {
        return rolesDao.getListRoles();
    }

    @Override
    public boolean roleIsExist(String role) {
        return rolesDao.roleIsExist(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return rolesDao.getRoleByName(roleName);
    }
}
