package com.cs633.team4.clouddatamanagementsystem.mapper;

import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.apache.ibatis.annotations.*;

/**
 * MyBatis mapper interfaces to connect User model class with data in USERS table of database.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, " +
            "#{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insert(User user);

    @Update("UPDATE USERS SET username = #{username}, password = #{password}, firstname = #{firstName}, " +
            "lastname = #{lastName} WHERE userid = #{userId}")
    void update(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    void delete(Integer userId);
}
