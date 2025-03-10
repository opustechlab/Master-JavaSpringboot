package com.oputechlab.belajarcrud3.implement;

import com.oputechlab.belajarcrud3.repository.UserRepository;
import com.oputechlab.belajarcrud3.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Map<String, Object>> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public int deleteUser(int id) {
        return userRepository.deleteUser(id);
    }
}

