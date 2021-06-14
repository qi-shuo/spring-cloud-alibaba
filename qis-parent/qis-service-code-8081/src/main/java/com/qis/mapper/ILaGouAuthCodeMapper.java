package com.qis.mapper;

import com.qis.vo.LaGouAuthCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @author qishuo
 * @date 2021/5/30 6:36 下午
 */
public interface ILaGouAuthCodeMapper {
    /**
     * 新增
     *
     * @param laGouAuthCode
     */
    @Insert("insert into lagou_auth_code (email,code,createtime,expiretime) " +
            "values (#{email},#{code},now(),#{expiretime})")
    void save(LaGouAuthCode laGouAuthCode);

    /**
     * 获取最后一次验证码
     *
     * @param email 邮箱
     * @param date  当前时间
     * @return
     */
    @Select("select id,email,code,createtime,expiretime from lagou_auth_code " +
            "where email=#{email} and expiretime>#{expiretime} order by createtime desc limit 1")
    LaGouAuthCode getLastLaGouAuthCode(@Param("email") String email, @Param("expiretime") Date date);
}
