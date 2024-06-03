package ru.itmentor.spring.boot_security.demo.service;



import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    void save(User user);
    void update(Integer id, User user);
    void deleteById(Integer id);
}
