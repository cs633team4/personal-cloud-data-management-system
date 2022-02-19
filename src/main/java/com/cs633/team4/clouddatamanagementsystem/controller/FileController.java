package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.File;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.FileService;
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
 * Handle front-end calls, identify home.html template served for file request and populate view model with data.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private final UserService userService;
    private final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadImage (@RequestParam("fileUpload") MultipartFile file, Authentication auth, Model model)
            throws IOException {

        try {
            // check if file is selected
            if (file.isEmpty()) {
                model.addAttribute("error", true);
                model.addAttribute("message", "No file is selected. Please select a file to upload.");
                return "result";
            }

            // check if file size is no larger than 1MB
            if (file.getSize() > 1000000) {
                // throw new MaxUploadSizeExceededException(file.getSize());
                model.addAttribute("error", true);
                model.addAttribute("message", "File size is too large. Please don't exceed 1MB.");
                return "result";
            }

            // check if file name exits
            if (!fileService.isFileNameAvailable(file.getOriginalFilename())) {
                model.addAttribute("error", true);
                model.addAttribute("message", "File with the same name already exists.");
                return "result";
            }

            User user = userService.getUser(auth.getName());
            File newFile = new File(null, file.getOriginalFilename(), file.getContentType(),
                    Long.toString(file.getSize()), file.getBytes(), user.getUserId());

            // upload file
            fileService.addImage(newFile);
            model.addAttribute("success", true);
            model.addAttribute("message", "Upload file successfully!");

        } catch (IOException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when uploading file. Please try again.");
        }

        return "result";
    }


    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        // @PathVariable is used to handle template variables in the request URI mapping, and set them as method parameters.

        try {
            this.fileService.deleteFile(fileId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Delete file successfully!");

        }catch (Exception e){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when deleting File.");
        }

        return "result";
    }

    //
    @GetMapping("/view/{fileId}")
    public ResponseEntity viewFile(@PathVariable Integer fileId){
        // ResponseEntity represents the whole HTTP response: status code, headers, and body.
        // Use it to fully configure the HTTP response.

        File file = fileService.getImage(fileId);

        // set custom contentType, header, and body
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                // replace "attachment" with "inline" if view file in new browser tab instead of download file directly
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getImageName()+"\"")
                .body(file.getImageData());
    }

}

