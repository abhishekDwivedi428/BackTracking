package com.backtracking.BackTracking.controller;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.service.ApplicationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void getAllApplication() throws Exception {
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        application.setId(1L);
        application.setName("Abhishek");
        application.setDescription(" Fake Description");
        application.setOwner("Dwivedi");
        applications.add(application);
        when(applicationService.findAllApplication()).thenReturn(applications);
        // Create a get request with an url & accept header for application/json
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getAllApplication1() throws Exception {
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        application.setId(1L);
        application.setName("Abhishek");
        application.setDescription(" Fake Description");
        application.setOwner("Dwivedi");
        applications.add(application);
        when(applicationService.findAllApplication()).thenReturn(applications);
        // Create a get request with an url & accept header for application/json
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(jsonPath("$", Matchers.hasSize(1))).andDo(print());
    }

    @Test
    public void getAllApplicationsWithException() throws Exception {
        when(applicationService.findAllApplication()).thenThrow(new NullPointerException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        //Assert that the return status is OK
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getApplicationById()
    {
        Application application = new Application();
    }

    @Test
    public void testSaveApplication() throws Exception {
        Application application = new Application(2L, "Test", "desc", "Abhishek");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications")
            .content(asJsonString(application))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated());
    }



    private String asJsonString(final Object ob)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            return jsonContent;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
