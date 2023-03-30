package com.example.demo.mapper;
import java.util.List;
import com.example.demo.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    Long save(Role role);

    Long update(Role role);

    Role findById(Long id);

    List<Role> find();

    List<Role> findByName(String name);

    void delete(Long id);

}
