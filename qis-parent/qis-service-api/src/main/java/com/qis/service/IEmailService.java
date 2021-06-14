package com.qis.service;

/**
 * @author qishuo
 * @date 2021/6/13 11:48 下午
 */
public interface IEmailService {
    /**
     * 发送验证码
     *
     * @param email
     * @param code
     * @return
     */
    boolean sendEmail(String email, String code);
}
