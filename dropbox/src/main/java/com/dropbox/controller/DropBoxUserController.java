package com.dropbox.controller;

import com.dropbox.document.DropBoxUser;
import com.dropbox.service.DropBoxUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONException;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DropBoxUserController {

    private DropBoxUserService dropBoxUserService;

    @Autowired
    DropBoxUserController(DropBoxUserService  dropBoxUserService) {
        this.dropBoxUserService = dropBoxUserService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE
    )//, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDropBoxUser(@RequestBody DropBoxUser dropBoxUser) {
        System.out.println("came to register section with request body: "+dropBoxUser.toString());
        dropBoxUserService.userSignUp(dropBoxUser);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE
    , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDropBoxUser(@RequestBody String credentials) throws JSONException {
//        System.out.println("came to register section with request body: "+dropBoxUser.toString());
        JSONObject json = null;
//        try{
            json = new JSONObject(credentials);
//        }
//        catch (JSONException exp){
//            System.out.println(exp);
//        }
        DropBoxUser dropBoxUser = dropBoxUserService.getDropBoxUser(json.getString("username").toString());
        System.out.println("user details:"+dropBoxUser.toString());
        JSONObject dropBoxUserJson = new JSONObject(dropBoxUser);
        if(dropBoxUserJson.getString("userPass") != json.getString("password")){
            return new ResponseEntity<>("error",HttpStatus.UNAUTHORIZED);
        }
        JSONObject userData = new JSONObject();
        userData.put("userData",dropBoxUserJson);
        System.out.println(userData);
//        System.out.println(json);
//        dropBoxUserService.userSignUp(dropBoxUser);
        return new ResponseEntity<>(userData.toString(), HttpStatus.OK);
    }
}
