package com.oputechlab.belajarcrud3.controller;

import com.oputechlab.belajarcrud3.configuration.CoreConfiguration;
import com.oputechlab.belajarcrud3.model.MyRequest;
import com.oputechlab.belajarcrud3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account") // Prefix URL
public class AccountController {

    private final Map<Integer, String> users = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final UserService userService;
    private final CoreConfiguration coreConfiguration;


    public AccountController(UserService userService, CoreConfiguration coreConfiguration) {
        this.userService = userService;
        this.coreConfiguration = coreConfiguration;
    }

    // Get User by ID
    @GetMapping("/get")
    public ResponseEntity<String> getUserById() {
        return ResponseEntity.ok("App Name: " + coreConfiguration.getName() +
                ", Version: " + coreConfiguration.getVersion());
    }

    @PostMapping("/submit")
    public List<Map<String, Object>> submitData(@RequestBody MyRequest request) {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody MyRequest request) {
        try{
            userService.deleteUser(request.getId());
            return ResponseEntity.ok("OK");
        }
        catch (DataAccessException e){
            logger.error(e.getMessage(), "Error!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            logger.error(e.getMessage(), "Error!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
