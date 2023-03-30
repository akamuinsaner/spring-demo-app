package com.example.demo.mapper;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

    User findByUserId(Long id);

    Long save(User user);


}
