package com.example.demo.service;

import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public Long save(Role role, List<Long> permissionIds) {
        roleMapper.save(role);
        Long roleId = role.getId();
        if (permissionIds.size() > 0) {
            rolePermissionMapper.save(roleId, permissionIds);
        }
        return roleId;
    }

    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    public List<Role> find() {
        return roleMapper.find();
    }

    public void delete(Long id) {
        roleMapper.delete(id);
        Map<String, Long> map = new HashMap<>();
        map.put("roleId", id);
        rolePermissionMapper.delete(map);
    }

}
