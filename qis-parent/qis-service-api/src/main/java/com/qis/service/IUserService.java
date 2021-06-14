package com.qis.service;

/**
 * @author qishuo
 * @date 2021/6/13 11:49 下午
 */
public interface IUserService {
    /**
     * 是否注册
     *
     * @param email
     * @return
     */
    boolean isRegistered(String email);

    /**
     * 注册
     *
     * @param email
     * @param password
     * @param code
     * @return
     */
    Integer register(String email, String password, String code);

    /**
     * 登录
     *
     * @param email
     * @param password
     * @return
     */
    String login(String email, String password);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    String userInfo(String token);
}
