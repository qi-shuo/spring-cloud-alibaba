package com.qis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qishuo
 * @date 2021/5/30 5:42 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaGouUser {
    private Long id;
    private String email;
    private String password;
    private Date createtime;
    private Date updatetime;
}
