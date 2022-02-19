package com.cs633.team4.clouddatamanagementsystem.model;

/**
 * POJO with fields that match the names and data types of FILES table in database schema.
 */
public class File {

    private Integer fileId; // primary key
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;
    private Integer userId; // foreign key

    public File(Integer fileId, String fileName, String contentType, String fileSize, byte[] fileData, Integer userId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.userId = userId;
    }

    public Integer getImageId() {
        return fileId;
    }

    public void setImageId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getImageName() {
        return fileName;
    }

    public void setImageName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getImageSize() {
        return fileSize;
    }

    public void setImageSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getImageData() {
        return fileData;
    }

    public void setImageData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
