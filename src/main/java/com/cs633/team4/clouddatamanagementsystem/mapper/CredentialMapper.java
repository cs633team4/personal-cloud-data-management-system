package com.cs633.team4.clouddatamanagementsystem.mapper;

import com.cs633.team4.clouddatamanagementsystem.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis mapper interfaces to connect Credential model class with data in CREDENTIALS table of database.
 */
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId} AND url = #{url}")
    Credential getCredentialByUserAndUrl(Integer userId, String url);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getCredentialsByUser(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, " +
            "#{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, " +
            "password = #{password} WHERE credentialid = #{credentialId}")
    Integer update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Integer delete(Integer credentialId);
}