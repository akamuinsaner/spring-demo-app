package com.example.demo.controller;
import java.util.List;
import com.example.demo.model.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @PostMapping("/save")
    public Long save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return permission.getId();
    }

    @PostMapping("/update")
    public Long update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return permission.getId();
    }

    @GetMapping("/findById")
    public Permission findById(@RequestParam("id") Long id) {
        return permissionService.findById(id);
    }

    @GetMapping("/findByName")
    public List<Permission> findByName(@RequestParam("name") String name) {
        return permissionService.findByName(name);
    }

    @GetMapping("/find")
    public List<Permission> find() { return permissionService.find(); }

    @PostMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        permissionService.delete(id);
    }

}
