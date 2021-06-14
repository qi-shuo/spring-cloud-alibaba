package com.qis.controller;

import com.qis.service.IEmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qishuo
 * @date 2021/5/30 6:58 下午
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    @Resource
    private IEmailService emailService;

    @GetMapping("/{email}/{code}")
    public boolean semEmail(@PathVariable String email, @PathVariable String code) {
        return emailService.sendEmail(email, code);
    }
}
