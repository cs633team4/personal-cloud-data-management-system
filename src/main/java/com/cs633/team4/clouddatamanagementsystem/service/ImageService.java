package com.cs633.team4.clouddatamanagementsystem.service;

import com.cs633.team4.clouddatamanagementsystem.mapper.ImageMapper;
import com.cs633.team4.clouddatamanagementsystem.model.Image;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageMapper imageMapper;

    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public Integer addImage(Image image) { return imageMapper.insert(image);}

    public Integer updateImage(Image image) {
        return imageMapper.update(image);
    }

    public Integer deleteImage(Integer imageId) {
        return imageMapper.delete(imageId);
    }

    public Image getImage(Integer imageId) {
        return imageMapper.getImage(imageId);
    }

    public List<Image> getAllImages(User user) {
        return imageMapper.getAllImagesByUser(user.getUserId());
    }

    public boolean isImageNameAvailable(String imageName) {
        return imageMapper.getImageByName(imageName) == null;
    }
}
