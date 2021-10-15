package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdata.configs.SecurityConfig;
import ru.gb.springdata.dtos.UserDto;
import ru.gb.springdata.exceptions.DataValidationException;
import ru.gb.springdata.exceptions.MarketError;
import ru.gb.springdata.model.Role;
import ru.gb.springdata.model.User;
import ru.gb.springdata.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserDto createNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        Collection<Role> roles = roleService.findAll();
        roles.clear();
        roles.add(roleService.getStandartRole());
        user.setRoles(roles);
        userRepository.save(user);
        user.setPassword(null);
        return new UserDto(user);
    }
}
