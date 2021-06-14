package com.qis.mapper;

import com.qis.vo.LaGouToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author qishuo
 * @date 2021/5/30 5:46 下午
 */
public interface ILaGouTokenMapper {
    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @Select("select id,email,token from lagou_token where token=#{token}")
    LaGouToken findByToken(@Param("token") String token);

    /**
     * 新增
     *
     * @param laGouToken
     */
    @Insert("insert into lagou_token (email,token) " +
            "values (#{email},#{token})")
    void save(LaGouToken laGouToken);
}
