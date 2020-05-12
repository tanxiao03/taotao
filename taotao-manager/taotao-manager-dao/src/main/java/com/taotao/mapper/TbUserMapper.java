package com.taotao.mapper;

import com.taotao.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbUserMapper {

    /**
     * 用户校验 校验用户名
     * @param param
     * @return
     */
    @Select("SELECT count(*) FROM tbuser WHERE userName = #{param}")
    int findUserByName(String param);

    /**
     * 用户校验 校验电话号码
     * @param param
     * @return
     */
    @Select("SELECT count(*) FROM tbuser WHERE phone = #{param}")
    int findUserByPhone(String param);

    /**
     * 用户校验 校验邮箱
     * @param param
     * @return
     */
    @Select("SELECT count(*) FROM tbuser WHERE email = #{param}")
    int findUserByEmail(String param);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @Insert("INSERT INTO tbuser(userName, passWord, phone, email, created, updated) VALUE (#{userName},#{passWord},#{phone},#{email},#{created},#{updated})")
    int addUser(TbUser tbUser);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     */
    @Select("SELECT * FROM tbuser WHERE userName = #{userName} AND passWord = #{passWord}")
    TbUser findUserByNameAndPass(@Param("userName")String userName, @Param("passWord")String passWord);
}