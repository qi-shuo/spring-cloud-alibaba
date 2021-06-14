package com.qis.service.impl;

import com.qis.mapper.ILaGouTokenMapper;
import com.qis.mapper.ILaGouUserMapper;
import com.qis.service.IAuthCodeService;
import com.qis.service.IUserService;
import com.qis.vo.LaGouToken;
import com.qis.vo.LaGouUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author qishuo
 * @date 2021/5/30 4:37 下午
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Resource
    private ILaGouUserMapper laGouUserMapper;
    @Resource
    private ILaGouTokenMapper laGouTokenMapper;
    @Reference(check = false)
    private IAuthCodeService authCodeService;

    @Override
    public boolean isRegistered(String email) {
        return Optional.ofNullable(laGouUserMapper.findByEmail(email)).isPresent();
    }

    @Override
    public Integer register(String email, String password, String code) {
        LaGouUser laGouUser = LaGouUser.builder().email(email).password(password).build();
        Integer validate = authCodeService.validate(email, code);
        log.info("验证码验证,email:{},code:{},状态:{}", email, code, validate);
        if (validate > 0) {
            return validate;
        }
        laGouUserMapper.save(laGouUser);
        return validate;
    }

    @Override
    public String login(String email, String password) {

        LaGouUser emailAndPassword = laGouUserMapper.findByEmailAndPassword(email, password);
        if (Objects.isNull(emailAndPassword)) {
            return null;
        }
        String token = getToken();
        LaGouToken laGouToken = LaGouToken.builder().email(email).token(token).build();
        laGouTokenMapper.save(laGouToken);
        return token;
    }

    @Override
    public String userInfo(String token) {
        return Optional.ofNullable(laGouTokenMapper.findByToken(token)).map(LaGouToken::getEmail).orElse(null);
    }

    private String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
