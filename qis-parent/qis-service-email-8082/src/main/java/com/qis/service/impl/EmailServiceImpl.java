package com.qis.service.impl;

import com.qis.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

/**
 * @author qishuo
 * @date 2021/5/30 6:43 下午
 */
@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private MailProperties mailProperties;

    @Override
    public boolean sendEmail(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailProperties.getUsername());
            message.setTo(email);
            message.setSubject("登录验证码");
            message.setText(code);
            mailSender.send(message);

        } catch (Exception e) {
            log.info("发送邮件异常:", e);
            return false;
        }

        return true;
    }
}
