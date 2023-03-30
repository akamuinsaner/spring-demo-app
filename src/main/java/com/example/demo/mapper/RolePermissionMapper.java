package com.example.demo.mapper;
import java.util.List;
import java.util.Map;

import com.example.demo.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {

    Long save(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    Long delete(Map<String, Long> params);
}
