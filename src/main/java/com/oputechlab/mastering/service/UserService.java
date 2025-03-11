package com.oputechlab.mastering.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getAllUsers();
    int deleteUser(int id);
}