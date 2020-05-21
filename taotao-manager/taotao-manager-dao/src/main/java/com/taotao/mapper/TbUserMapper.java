package com.taotao.mapper;

import com.taotao.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("INSERT INTO tbuser(userName, passWord, phone, email, created, updated,status) VALUE (#{userName},#{passWord},#{phone},#{email},#{created},#{updated},#{status})")
    int addUser(TbUser tbUser);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     */
    @Select("SELECT * FROM tbuser WHERE userName = #{userName} AND passWord = #{passWord}")
    TbUser findUserByNameAndPass(@Param("userName")String userName, @Param("passWord")String passWord);

    /**
     * 修改数据库中的用户状态
     * @param i
     * @param id
     */
    @Update("UPDATE tbuser SET status = #{i} WHERE id = #{id}")
    void updateUser(@Param("i")int i,@Param("id") Long id);

    /**
     * 定时器触发后，将所有的用户状态都改成0
     * @param i
     */
    @Update("UPDATE tbuser SET status = #{i}")
    void updateAllUser(int i);
}