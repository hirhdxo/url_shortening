package com.task.musinsa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

@AutoConfigureMockMvc
public abstract class BaseSpringMockTest<T> extends BaseSpringTest {
    @Autowired
    protected MockMvc mockMvc;

    /**
     * ObjectMapper
     * json <-> object 변환
     * SpringBoot에서 지원
     */
    @Autowired
    protected ObjectMapper objectMapper;

    protected String convertToJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T convertToJson(MvcResult result, Class<T> clazz) throws IOException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), clazz);
    }
}
