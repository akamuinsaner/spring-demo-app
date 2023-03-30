package com.example.demo.controller;
import java.util.Map;
import java.util.List;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/save")
    public Long save(@RequestBody Map<String, Object> params) {
        List<Long> permissionIds = (List<Long>) params.get("permissionIds");
        String name = (String) params.get("name");
        String chineseName = (String) params.get("chineseName");
        Role role = new Role();
        role.setName(name);
        role.setChineseName(chineseName);
        return roleService.save(role, permissionIds);
    }

    @GetMapping("/findById")
    public Role findById(@RequestParam("id") Long id) {
        return roleService.findById(id);
    }

    @GetMapping("/find")
    public List<Role> find() {
        return roleService.find();
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        roleService.delete(id);
    }
}
