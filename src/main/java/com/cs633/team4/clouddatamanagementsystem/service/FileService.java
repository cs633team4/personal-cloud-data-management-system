package com.cs633.team4.clouddatamanagementsystem.service;

import com.cs633.team4.clouddatamanagementsystem.mapper.FileMapper;
import com.cs633.team4.clouddatamanagementsystem.model.File;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public Integer addFile(File file) { return fileMapper.insert(file);}

    public Integer updateFile(File file) {
        return fileMapper.update(file);
    }

    public Integer deleteFile(Integer fileId) {
        return fileMapper.delete(fileId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getAllFiles(User user) {
        return fileMapper.getAllFilesByUser(user.getUserId());
    }

    public boolean isFileNameAvailable(String fileName) {
        return fileMapper.getFileByName(fileName) == null;
    }
}
