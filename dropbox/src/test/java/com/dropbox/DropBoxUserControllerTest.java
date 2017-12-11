package com.dropbox;

import com.dropbox.controller.DropBoxUserController;
import com.dropbox.document.DropBoxUser;
import com.dropbox.service.DropBoxUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
//import net.minidev.json.JSONObject;
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
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

public class DropBoxUserControllerTest {
    @Mock
    private DropBoxUserService dropBoxUserService;

    @InjectMocks
    private DropBoxUserController dropBoxUserController;

    @Autowired
//    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(dropBoxUserController).build();
    }

    @Test
    public void loginTestFail() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("varun.jindal1292@gmail.com");
        dropBoxUser.setUserPass("jindal");


        JSONObject userDetals = new JSONObject();
        userDetals.put("username","varun.jindal1292@gmail.com");
        userDetals.put("password","jindal");
//        String userDetails = "{\"username\":\"varun.jindal1292@gmail.com\",\"password\":\"jindal\"}";

//        String userDetails = (new ObjectMapper()).valueToTree(userDetals).toString();
        DropBoxUser mockUser = new DropBoxUser();
        mockUser.setUserPass("jindal2");
        mockUser.setEmailID("varun.jindal1292@gmail.com");
        Mockito.when(dropBoxUserService.getDropBoxUser(dropBoxUser.getEmailID())).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetals.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = testResult.getResponse();

        assertEquals(401, response.getStatus());
    }

    @Test
    public void registerTestSuccess() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("varun.jindal1292@gmail.com");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");



        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(201, mockHttpServletResponse.getStatus());
    }
    @Test
    public void registerTestFail() throws Exception {
        DropBoxUser dropBoxUser = new DropBoxUser();
        dropBoxUser.setEmailID("");
        dropBoxUser.setUserPass("jindal");
        dropBoxUser.setFirstName("varun");
        dropBoxUser.setFirstName("jindal");



        String userDetails = (new ObjectMapper()).valueToTree(dropBoxUser).toString();
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(userDetails)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult testResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = testResult.getResponse();

        assertEquals(400, mockHttpServletResponse.getStatus());
    }

}
