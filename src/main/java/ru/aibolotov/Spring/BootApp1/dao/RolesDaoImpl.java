package ru.aibolotov.Spring.BootApp1.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.Spring.BootApp1.model.Role;
import ru.aibolotov.Spring.BootApp1.repository.RoleRepository;

import java.util.List;


@Repository
@Transactional
public class RolesDaoImpl implements RolesDao {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getListRoles() {
        return roleRepository.findAll();
    }

    @Override
    public boolean roleIsExist(String role) {
        List<Role> roles = getListRoles();
        int size = roles.size();
        for (int i = 0; i < size; i++) {
            if (roles.get(i).getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findAllByRole(roleName);
    }
}
