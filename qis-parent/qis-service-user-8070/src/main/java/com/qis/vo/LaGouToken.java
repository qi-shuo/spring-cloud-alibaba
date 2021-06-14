package com.qis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qishuo
 * @date 2021/5/30 5:43 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaGouToken {
    private Long id;
    private String email;
    private String token;
}
