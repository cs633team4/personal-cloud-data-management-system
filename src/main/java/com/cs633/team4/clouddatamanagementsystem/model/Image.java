package com.cs633.team4.clouddatamanagementsystem.model;

/**
 * POJO with fields that match the names and data types of FILES table in database schema.
 */
public class Image {

    private Integer imageId; // primary key
    private String imageName;
    private String contentType;
    private String imageSize;
    private byte[] imageData;
    private Integer userId; // foreign key

    public Image(Integer imageId, String imageName, String contentType, String imageSize, byte[] imageData, Integer userId) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.contentType = contentType;
        this.imageSize = imageSize;
        this.imageData = imageData;
        this.userId = userId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
