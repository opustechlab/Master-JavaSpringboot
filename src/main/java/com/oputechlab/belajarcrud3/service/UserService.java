package com.oputechlab.belajarcrud3.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getAllUsers();
    int deleteUser(int id);
}