package com.qis.service.impl;

import com.qis.service.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author qishuo
 * @date 2021/5/30 6:53 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceImplTest {
    @Resource
    private IEmailService emailService;

    @Test
    public void sendEmail() {
        emailService.sendEmail("1677343737@qq.com","123234");
    }
}