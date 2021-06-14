package com.qis.controller;

import com.qis.service.IAuthCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qishuo
 * @date 2021/5/30 7:11 下午
 */
@RestController
@RequestMapping("/code")
public class AuthCodeController {
    @Resource
    private IAuthCodeService authCodeService;

    @GetMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable String email, @PathVariable String code) {
        return authCodeService.validate(email, code);
    }

    @GetMapping("/create/{email}")
    public boolean createCode(@PathVariable String email) {
        return authCodeService.createCode(email);
    }
}
