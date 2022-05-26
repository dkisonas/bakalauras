package com.bakalauras.backend.service;

import com.bakalauras.backend.models.users.User;
import com.bakalauras.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.getById(id);
    }
}
