package com.qis.controller;

import com.qis.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qishuo
 * @date 2021/5/30 4:36 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable String email) {
        return userService.isRegistered(email);
    }

    @GetMapping("/register/{email}/{password}/{code}")
    public Integer register(@PathVariable String email, @PathVariable String password, @PathVariable String code) {
        try {
            return userService.register(email, password, code);
        } catch (Exception e) {
            return 1;
        }

    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                String name = cookie.getName();
                if ("token".equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        String token = userService.login(email, password);
        Cookie cookie = new Cookie("token", token);
        //cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        return token;
    }

    @GetMapping("/info/{token}")
    public String userInfo(@PathVariable String token) {
        return userService.userInfo(token);
    }
}
