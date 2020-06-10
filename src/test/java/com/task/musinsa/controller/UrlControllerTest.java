package com.task.musinsa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.task.musinsa.BaseSpringMockTest;
import com.task.musinsa.data.UrlData;
import com.task.musinsa.data.UrlData.RequestUrlData;
import com.task.musinsa.repository.UrlInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UrlControllerTest extends BaseSpringMockTest {
    @Autowired
    UrlInfoRepository urlInfoRepository;

    private RequestUrlData reqUrlData;

    @BeforeEach
    void init() {
        reqUrlData = new RequestUrlData("http://naver.com");

    }

    @Test
    void convert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqUrlData)))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
