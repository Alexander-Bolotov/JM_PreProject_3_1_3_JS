package ru.aibolotov.Spring.BootApp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aibolotov.Spring.BootApp1.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findAllByRole(String role);
}
