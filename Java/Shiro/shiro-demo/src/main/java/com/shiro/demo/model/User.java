package com.shiro.demo.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/2/26
 */
@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
