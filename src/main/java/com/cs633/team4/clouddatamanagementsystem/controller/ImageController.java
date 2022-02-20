package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.File;
import com.cs633.team4.clouddatamanagementsystem.model.Image;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.FileService;
import com.cs633.team4.clouddatamanagementsystem.service.ImageService;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Handle front-end calls, identify home.html template served for image request and populate view model with data.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private final UserService userService;
    private final ImageService imageService;

    public ImageController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public String uploadImage (@RequestParam("imageUpload") MultipartFile file, Authentication auth, Model model)
            throws IOException {

        try {
            // check if file is selected
            if (file.isEmpty()) {
                model.addAttribute("error", true);
                model.addAttribute("message", "No image is selected. Please select a file to upload.");
                return "result";
            }

            // check if file size is no larger than 10MB
            if (file.getSize() > 10000000) {
                // throw new MaxUploadSizeExceededException(file.getSize());
                model.addAttribute("error", true);
                model.addAttribute("message", "Image size is too large. Please don't exceed 10MB.");
                return "result";
            }

//            // check if file name exits
//            if (!imageService.isImageNameAvailable(file.getOriginalFilename())) {
//                model.addAttribute("error", true);
//                model.addAttribute("message", "Image with the same name already exists.");
//                return "result";
//            }

            User user = userService.getUser(auth.getName());
            Image newFile = new Image(null, file.getOriginalFilename(), file.getContentType(),
                    Long.toString(file.getSize()), file.getBytes(), user.getUserId());

            // upload file
            imageService.addImage(newFile);
            model.addAttribute("success", true);
            model.addAttribute("message", "Upload image successfully!");

        } catch (IOException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when uploading file. Please try again.");
        }

        return "result";
    }


    @GetMapping("/delete/{imageId}")
    public String deleteImage(@PathVariable Integer imageId, Model model) {
        // @PathVariable is used to handle template variables in the request URI mapping, and set them as method parameters.

        try {
            this.imageService.deleteImage(imageId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Delete image successfully!");

        }catch (Exception e){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when deleting image.");
        }

        return "result";
    }

    //
    @GetMapping("/view/{imageId}")
    public ResponseEntity viewImage(@PathVariable Integer imageId){
        // ResponseEntity represents the whole HTTP response: status code, headers, and body.
        // Use it to fully configure the HTTP response.

        Image image = imageService.getImage(imageId);

        // set custom contentType, header, and body
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                // replace "attachment" with "inline" if view image in new browser tab instead of download image directly
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+image.getImageName()+"\"")
                .body(image.getImageData());
    }

}

