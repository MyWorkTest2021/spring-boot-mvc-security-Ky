package us.peaksoft.springbootmvcsecurity.service;

import us.peaksoft.springbootmvcsecurity.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> getAllUsers();

    void delete(User user);

    void update(User user);

    User getById(Long id);

    User findByUsername(String username);

    User findUserNyEmail(String email);
}
