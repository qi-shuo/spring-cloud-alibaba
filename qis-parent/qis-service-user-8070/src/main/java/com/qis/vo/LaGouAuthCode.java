package com.qis.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author qishuo
 * @date 2021/5/30 5:40 下午
 */
@Data
public class LaGouAuthCode {
    private Long id;
    private String email;
    private String code;
    private Date createtime;
    private Date expiretime;
}
