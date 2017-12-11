package com.dropbox.controller;

import com.dropbox.document.DropBoxUser;
import com.dropbox.document.Files;
import com.dropbox.service.FileService;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "files")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {

    private FileService fileService;

    @Autowired
    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    private static final Logger logger = LoggerFactory
            .getLogger(FileController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?>
    uploadFileHandler(@RequestParam("user") String name,
                      @RequestParam("file") MultipartFile file) {
        System.out.println("I came here and user is " + name);
        ArrayList<String> emptyCoowner =  new ArrayList<>(Arrays.asList(""));

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File("public/" + name + "_dir");
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
                Files userFile = new Files();
                userFile.setFileName(file.getOriginalFilename());
                userFile.setIsDeleted("N");
                userFile.setOwner(name);
                userFile.setPath("http://localhost:8080/"+name+"_dir/"+file.getOriginalFilename());
                userFile.setUploadDate(new Date().toString());
                userFile.setCoowner(emptyCoowner);
                fileService.saveFile(userFile);
                System.out.println("user file is: "+userFile.toString());

                return new ResponseEntity<>("You successfully uploaded file=" + file.getOriginalFilename(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("You failed to upload " + name + " => " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("You failed to upload " + name
                    + " because the file was empty.", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/listFiles", method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    listFileHandler(@RequestBody DropBoxUser dropBoxUser) {
        System.out.println("came to list files with user: "+dropBoxUser.getEmailID());
        if(dropBoxUser.getEmailID() == ""){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
        List<Files> files = fileService.getUserFiles(dropBoxUser.getEmailID());
        System.out.println(Arrays.toString(files.toArray()));
        /*JSONObject responseJson = new JSONObject();
        responseJson.put("files",files);*/
        return new ResponseEntity<>(files,HttpStatus.OK);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    deleteFileHandler(@RequestBody String reqBody) {

        JSONObject deleteParams = new JSONObject(reqBody);
        System.out.println("owner is : "+deleteParams.getString("owner"));
        if(deleteParams.getString("owner").equals("")){
            return new ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST);
        }
        Files file = fileService.getFile(deleteParams.getString("_id"),deleteParams.getString("owner"));
        file.setIsDeleted("Y");
        fileService.saveFile(file);
        /*JSONObject responseJson = new JSONObject();
        responseJson.put("files",files);*/
        return new ResponseEntity<>("File has been deleted",HttpStatus.OK);
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    shareFileHandler(@RequestBody String reqBody) {
        System.out.println("Came to share file with param: "+reqBody);
        JSONObject shareParams = new JSONObject(reqBody);
        if(shareParams.getString("owner").equals("")){
            return new ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST);
        }
        Files file = fileService.getFile(shareParams.getString("_id"),shareParams.getString("owner"));
        ArrayList<String> coowner = file.getCoowner();
        System.out.println("coonwer is :"+shareParams.getString("coowner"));
        coowner.add(shareParams.getString("coowner"));
        file.setCoowner(coowner);
        fileService.saveFile(file);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @RequestMapping(value = "/listShare", method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    listSharedFileHandler(@RequestBody DropBoxUser dropBoxUser) {
        System.out.println("Came to share file with param: "+dropBoxUser.toString());
        if(dropBoxUser.getEmailID().equals("")){
            return new ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST);
        }
        List<Files> files = fileService.getSharedFiles(dropBoxUser.getEmailID());
        return new ResponseEntity<>(files,HttpStatus.OK);
    }

}
