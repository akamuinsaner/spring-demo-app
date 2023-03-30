package com.example.demo.mapper;

import com.example.demo.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    Long save(Permission permission);

    Long update(Permission permission);

    Permission findById(Long id);

    List<Permission> findByName(String name);

    List<Permission> find();

    void delete(Long id);
}
