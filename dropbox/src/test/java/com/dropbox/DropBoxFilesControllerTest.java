package com.dropbox;

import com.dropbox.controller.FileController;
import com.dropbox.document.DropBoxUser;
import com.dropbox.document.Files;
import com.dropbox.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DropBoxFilesControllerTest {
    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    @Autowired
//    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void listFileTestSuccess() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("varun.jindal1292@gmail.com");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");


        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/listFiles")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(200, mockHttpServletResponse.getStatus());
    }
    @Test
    public void listFileTestFail() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");


        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/listFiles")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(400, mockHttpServletResponse.getStatus());
    }
    @Test
    public void deleteFileTestSuccess() throws Exception {
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("varun.jindal1292@gmail.com");

        Mockito.when(fileService.getFile(file.get_id(),file.getOwner())).thenReturn(file);

        String fileDetails = (new ObjectMapper()).valueToTree(file).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/delete")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(fileDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(200, mockHttpServletResponse.getStatus());
    }
    @Test
    public void deleteFileTestFail() throws Exception {
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("");

        Mockito.when(fileService.getFile(file.get_id(),file.getOwner())).thenReturn(file);

        String fileDetails = (new ObjectMapper()).valueToTree(file).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/delete")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(fileDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(400, mockHttpServletResponse.getStatus());
    }
    @Test
    public void shareFileTestSuccess() throws Exception {
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("varun.jindal1292@gmail.com");
        file.setCoowner(new ArrayList<>(Arrays.asList("sahil.sharma@gmail.com")));

        Mockito.when(fileService.getFile(file.get_id(),file.getOwner())).thenReturn(file);

        String fileDetails = (new ObjectMapper()).valueToTree(file).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/share")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(fileDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(200, mockHttpServletResponse.getStatus());
    }
    @Test
    public void shareFileTestFail() throws Exception {
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("");
        file.setCoowner(new ArrayList<>(Arrays.asList("sahil.sharma@gmail.com")));

        Mockito.when(fileService.getFile(file.get_id(),file.getOwner())).thenReturn(file);

        String fileDetails = (new ObjectMapper()).valueToTree(file).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/share")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(fileDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(400, mockHttpServletResponse.getStatus());
    }
    @Test
    public void listShareFileTestSuccess() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("varun.jindal1292@gmail.com");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("");
        file.setCoowner(new ArrayList<>(Arrays.asList("sahil.sharma@gmail.com")));
        List<Files> files = new ArrayList<Files>(Arrays.asList(file));

        Mockito.when(fileService.getSharedFiles(dropBoxUser.getEmailID())).thenReturn(files);

        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/listShare")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(200, mockHttpServletResponse.getStatus());
    }
    @Test
    public void listShareFileTestFail() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");
        Files file = new Files();
        file.set_id("ObjectId(\"5a2e143fd70c160af8b6ce49\")");
        file.setOwner("");
        file.setCoowner(new ArrayList<>(Arrays.asList("sahil.sharma@gmail.com")));
        List<Files> files = new ArrayList<Files>(Arrays.asList(file));

        Mockito.when(fileService.getSharedFiles(dropBoxUser.getEmailID())).thenReturn(files);

        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/files/listShare")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(400, mockHttpServletResponse.getStatus());
    }
}
