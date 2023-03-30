package com.example.demo.service;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            return user;
        }
    }


    public User findByUserId(Long id) {
        return userMapper.findByUserId(id);
    }

    public Long save(User user) {
        return userMapper.save(user);
    }

    public void addRoleToUsers(Long userId, List<Long> roleIds) {
        userRoleMapper.save(userId, roleIds);
    }
}
