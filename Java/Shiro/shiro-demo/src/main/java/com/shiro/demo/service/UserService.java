package com.shiro.demo.service;

import com.shiro.demo.model.User;

/**
 * @author Angus
 * @date 2019/2/26
 */
public interface UserService {
    User findByUsername(String username);
}
