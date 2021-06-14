package com.qis.service.impl;

import com.qis.service.IUserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author qishuo
 * @date 2021/5/30 6:12 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest extends TestCase {
    @Resource
    private IUserService userService;
    @Test
    public void testUserInfo() {
        System.out.println(userService.userInfo("token"));
    }
}