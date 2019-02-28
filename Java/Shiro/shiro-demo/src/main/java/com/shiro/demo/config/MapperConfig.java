package com.shiro.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Angus
 * @date 2019/2/26
 */
@Configuration
@MapperScan(basePackages = {"com.shiro.demo.mapper"})
public class MapperConfig {
}
