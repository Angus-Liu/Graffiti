package com.shiro.demo.mapper;

import com.shiro.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Angus
 * @date 2019/2/26
 */
public interface UserMapper {
    User findByUsername(@Param("username") String username);
}
