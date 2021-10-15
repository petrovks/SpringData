package ru.gb.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springdata.model.Role;
import ru.gb.springdata.model.User;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
