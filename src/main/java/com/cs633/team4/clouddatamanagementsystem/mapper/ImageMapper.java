package com.cs633.team4.clouddatamanagementsystem.mapper;

import com.cs633.team4.clouddatamanagementsystem.model.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis mapper interfaces to connect IMAGES model class with data in IMAGES table of database.
 */
@Mapper
public interface ImageMapper {
    @Select("SELECT * FROM IMAGES WHERE imageid = #{imageid}")
    Image getImage(Integer imageid);

    @Select("SELECT * FROM IMAGES WHERE imagename = #{imageName}")
    Image getImageByName(String imageName);

    @Select("SELECT * FROM IMAGES WHERE userid = #{userId}")
    List<Image> getAllImagesByUser(Integer userId);

    @Insert("INSERT INTO IMAGES (imagename, contenttype, imagesize, imagedata, userid) VALUES(#{imageName}, " +
            "#{contentType}, #{imageSize}, #{imageData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "imageId")
    Integer insert(Image image);

    @Update("UPDATE IMAGES SET imagename = #{imageName}, contenttype = #{contentType}, imagesize = #{imageSize}, " +
            "imagedata = #{imageData} WHERE imageid = #{imageId}")
    Integer update(Image image);

    @Delete("DELETE FROM IMAGES WHERE imageid = #{imageId}")
    Integer delete(Integer imageId);
}
