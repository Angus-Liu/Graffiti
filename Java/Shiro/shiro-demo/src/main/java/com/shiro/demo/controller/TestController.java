package com.shiro.demo.controller;

import com.shiro.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author Angus
 * @date 2019/2/26
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login-user")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/logout")
    public String logout() {
        Optional.ofNullable(SecurityUtils.getSubject())
                .ifPresent(Subject::logout);
        return "login";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/query")
    public String query() {
        return "query";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "edit";
    }
}
