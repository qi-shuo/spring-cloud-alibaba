package com.qis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qishuo
 * @date 2021/5/30 6:27 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaGouAuthCode {
    private Long id;
    private String email;
    private String code;
    private Date createtime;
    private Date expiretime;
}
