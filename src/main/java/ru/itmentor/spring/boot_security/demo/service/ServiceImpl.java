package ru.itmentor.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceImpl implements  UserService, RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user not found!");

        return user.get();
    }

    @Transactional
    @Override
    public void save(User user) {
        user.setRoles(getNewRoles(user));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(Integer id, User user) {

        User userToBeUpdated = findById(id);
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        user.setRoles(getNewRoles(user));
        userRepository.save(userToBeUpdated);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    private Set<Role> getNewRoles(User user) {

        Set<Role> newRoles = user.getRoles().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName()))
                .collect(Collectors.toSet());
        return newRoles;
    }
}
