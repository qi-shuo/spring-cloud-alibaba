package com.qis.service.impl;

import com.qis.mapper.ILaGouAuthCodeMapper;
import com.qis.service.IAuthCodeService;
import com.qis.service.IEmailService;
import com.qis.service.IUserService;
import com.qis.vo.LaGouAuthCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.Random;


/**
 * @author qishuo
 * @date 2021/5/30 6:27 下午
 */
@Service
@Slf4j
public class AuthCodeServiceImpl implements IAuthCodeService {

    @Resource
    private ILaGouAuthCodeMapper laGouAuthCodeMapper;
    @Reference(check = false)
    private IEmailService emailService;
    @Reference(check = false)
    private IUserService userService;

    @Override
    public Integer validate(String email, String code) {
        LaGouAuthCode lastLaGouAuthCode = laGouAuthCodeMapper.getLastLaGouAuthCode(email, new Date());
        if (Objects.isNull(lastLaGouAuthCode) || !code.equals(lastLaGouAuthCode.getCode())) {
            return 2;
        }
        return 0;
    }

    @Override
    public boolean createCode(String email) {
        boolean registered = userService.isRegistered(email);
        if (registered) {
            //已经注册过了
            return false;
        }
        LaGouAuthCode lastLaGouAuthCode = laGouAuthCodeMapper.getLastLaGouAuthCode(email, new Date());
        if (Objects.nonNull(lastLaGouAuthCode)) {
            return true;
        }
        Integer authCode = getAuthCode();
        log.info("authCode:{}", authCode);
        boolean sendResult = emailService.sendEmail(email, String.valueOf(authCode));
        if (sendResult) {
            long time = System.currentTimeMillis();
            laGouAuthCodeMapper.save(LaGouAuthCode.builder().email(email)
                    .code(String.valueOf(authCode))
                    .createtime(new Date(time))
                    .expiretime(new Date(time + 60000))
                    .build());
            return true;
        }
        return false;

    }

    private Integer getAuthCode() {
        return new Random().nextInt(1000000);
    }
}
