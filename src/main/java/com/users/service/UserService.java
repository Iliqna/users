package com.users.service;

import com.users.entities.User;
import com.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(final Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User <%s> does not exists!", id)));
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByFullName(final String name) {
        return userRepository.findUserByFullNameIgnoreCase(name);
    }

    public void save(final User user) {
        final User savedUser = userRepository.save(user);
        LOGGER.info("Created " + savedUser);
    }

    public void delete(final User user) {
        userRepository.deleteById(user.getId());
        LOGGER.info("Deleted " + user);
    }
}
