package ru.aibolotov.Spring.BootApp1.service;

import ru.aibolotov.Spring.BootApp1.model.Role;


import java.util.List;

public interface RoleService {
    List<Role> getListRoles();

    boolean roleIsExist(String role);

    Role getRoleByName(String roleName);
}
