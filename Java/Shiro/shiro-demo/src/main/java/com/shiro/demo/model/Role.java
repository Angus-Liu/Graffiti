package com.shiro.demo.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/2/26
 */
@Data
public class Role {
    private Integer rid;
    private String rname;
    private Set<Permission> permissions = new HashSet<>();
    // 为什么需要有 users？
    private Set<User> users = new HashSet<>();
}
