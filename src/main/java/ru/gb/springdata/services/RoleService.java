package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Role;
import ru.gb.springdata.repositories.RoleRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getStandartRole() {
        return roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Standart role is not found!"));
    }

    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
