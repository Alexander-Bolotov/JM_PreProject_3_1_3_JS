package ru.aibolotov.Spring.BootApp1.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.Spring.BootApp1.model.Role;


import java.util.List;

@Repository
@Transactional
public interface RolesDao {
    List<Role> getListRoles();

    boolean roleIsExist(String role);

    Role getRoleByName(String roleName);
}
