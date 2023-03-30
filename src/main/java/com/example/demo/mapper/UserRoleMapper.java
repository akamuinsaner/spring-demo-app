package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleMapper {

    void save(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    Long delete(Map<String, Long> params);
}
