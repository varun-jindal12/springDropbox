package com.dropbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
@RequestMapping(value="files")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
    private static final Logger logger = LoggerFactory
            .getLogger(FileController.class);
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?>
     uploadFileHandler(@RequestParam("user") String name,
                             @RequestParam("file") MultipartFile file) {
        System.out.println("I came here and user is "+name);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File("dropboxfiles/"+name+"_dir");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return new ResponseEntity<>("You successfully uploaded file=" + file.getOriginalFilename(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("You failed to upload " + name + " => " + e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("You failed to upload " + name
                    + " because the file was empty.",HttpStatus.BAD_REQUEST);
        }
    }
}
