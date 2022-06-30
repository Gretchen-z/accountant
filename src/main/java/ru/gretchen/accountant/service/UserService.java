package ru.gretchen.accountant.service;

import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreate(User user) {
        String username = user.getUsername();
        if (user.equals(get(username))) {
            return get(username);
        } else {
            return create(user);
        }
    }

    private User get(String username) {
        return userRepository.getByUsername(username);
    }

    private User create(User user) {
        return userRepository.save(user);
    }
}
