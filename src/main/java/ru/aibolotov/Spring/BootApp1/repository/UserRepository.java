package ru.aibolotov.Spring.BootApp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aibolotov.Spring.BootApp1.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByName(String name);
}
