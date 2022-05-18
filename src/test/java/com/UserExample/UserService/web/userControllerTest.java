package com.UserExample.UserService.web;

import com.UserExample.UserService.entity.AppUser;
import com.UserExample.UserService.service.UserService;
import com.UserExample.UserService.web.dto.GetUserInfoResponse;
import com.UserExample.UserService.web.dto.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class userControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void shouldReturnUserInfoWhenGivenCorrectId() throws Exception {
        //given
        Mockito.when(userService.getUserById(any(Long.class))).thenReturn(
                new GetUserInfoResponse(123L, "Bruce", 32)
        );
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/users/12345"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(123L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bruce"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(32));
    }

    @Test
    public void shouldReturnCreatedWhenOrderCreatedSucceed() throws Exception {
        //given
        UserInfo userInfo=new UserInfo("Will",28);

        Mockito.when(userService.createUser(any(UserInfo.class))).thenReturn(new AppUser());

        //when
        //then
        mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(userInfo))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isCreated());
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
