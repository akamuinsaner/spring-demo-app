package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Long processUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @GetMapping("/user/detailById")
    public User findByUserId(@RequestParam("id") Long id) {
        return userService.findByUserId(id);
    };

    @GetMapping("/user/detailByName")
    public UserDetails findByUsername(@RequestParam("username") String username) {
        logger.info("wqeqeeqwwrwqerewrwtrerterter");
        return userService.loadUserByUsername(username);
    };

    @PostMapping("/user/addRoles")
    public void addRoles(@RequestBody Map<String, Object> params) {
        Long userId = ((Number)params.get("userId")).longValue();
        List<Long> roleIds = (List<Long>) params.get("roleIds");
        userService.addRoleToUsers(userId, roleIds);
    }

}
