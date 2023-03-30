package com.example.demo.service;

import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public Long save(Permission permission) {
        return permissionMapper.save(permission);
    }

    public Long update(Permission permission) {
        return permissionMapper.update(permission);
    }

    public Permission findById(Long id) { return permissionMapper.findById(id); }

    public List<Permission> findByName(String name) { return permissionMapper.findByName(name); }

    public List<Permission> find() { return permissionMapper.find(); }

    public void delete(Long id) {
        permissionMapper.delete(id);
        Map<String, Long> map = new HashMap<>();
        map.put("permissionId", id);
        rolePermissionMapper.delete(map);
    }
}
