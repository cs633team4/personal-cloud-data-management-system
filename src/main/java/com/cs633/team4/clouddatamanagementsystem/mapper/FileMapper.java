package com.cs633.team4.clouddatamanagementsystem.mapper;

import com.cs633.team4.clouddatamanagementsystem.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis mapper interfaces to connect File model class with data in FILES table of database.
 */
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByName(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAllFilesByUser(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES(#{fileName}, " +
            "#{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Update("UPDATE FILES SET filename = #{fileName}, contenttype = #{contentType}, filesize = #{fileSize}, " +
            "filedata = #{fileData} WHERE fileid = #{fileId}")
    Integer update(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    Integer delete(Integer fileId);
}
