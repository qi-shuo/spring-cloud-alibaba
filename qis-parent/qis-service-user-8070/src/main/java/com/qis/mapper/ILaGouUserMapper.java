package com.qis.mapper;

import com.qis.vo.LaGouUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author qishuo
 * @date 2021/5/30 5:12 下午
 */
public interface ILaGouUserMapper {
    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    @Select("select id,email,password,createtime,updatetime from lagou_user where email=#{email}")
    LaGouUser findByEmail(@Param("email") String email);

    /**
     * 新增
     *
     * @param user
     */
    @Insert("insert into lagou_user (email,password,createtime,updatetime) " +
            "values (#{email},#{password},now(),now())")
    void save(LaGouUser user);

    /**
     * 根据邮箱和密码进行查询
     *
     * @param email
     * @param password
     * @return
     */
    @Select("select id,email,password,createtime,updatetime from lagou_user where email=#{email} and password=#{password}")
    LaGouUser findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
