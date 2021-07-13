package us.peaksoft.springbootmvcsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import us.peaksoft.springbootmvcsecurity.exceptions.UserNotFoundException;
import us.peaksoft.springbootmvcsecurity.model.User;
import us.peaksoft.springbootmvcsecurity.repository.UserRepository;
import us.peaksoft.springbootmvcsecurity.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Sorry user with this "
                + id + "not found in database"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public User findUserNyEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}