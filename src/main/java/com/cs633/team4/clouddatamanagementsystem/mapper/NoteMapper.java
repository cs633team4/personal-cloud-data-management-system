package com.cs633.team4.clouddatamanagementsystem.mapper;

import com.cs633.team4.clouddatamanagementsystem.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis mapper interfaces to connect Note model class with data in NOTES table of database.
 */
@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllNotesByUser(Integer userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer insert(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    Integer update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    Integer delete(Integer noteId);
}
