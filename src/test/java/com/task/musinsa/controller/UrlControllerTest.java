package com.task.musinsa.controller;

import com.task.musinsa.BaseSpringMockTest;
import com.task.musinsa.data.UrlData.RequestUrlData;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.repository.UrlInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.task.musinsa.data.ApiResponse.ApiStatus.VALIDATION_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UrlControllerTest extends BaseSpringMockTest {
    @Autowired
    UrlInfoRepository urlInfoRepository;

    private RequestUrlData validReqUrlData;
    private RequestUrlData invalidReqUrlData;

    @BeforeEach
    void init() {
        validReqUrlData = new RequestUrlData(VALID_TEST_URL);
        invalidReqUrlData = new RequestUrlData(INVALID_TEST_URL);
    }

    @Test
    void URL변환성공() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validReqUrlData)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.url").isNotEmpty())
            .andExpect(jsonPath("$.data.count").value(1));
    }

    @Test
    void 요청카운트테스트() throws Exception {
        UrlInfo urlInfo = UrlInfo.of(VALID_TEST_URL, VALID_TEST_SHORT_URL, SERVICE_DOMAIN);
        urlInfoRepository.save(urlInfo);

        assertThat(urlInfo.getCount()).isEqualTo(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validReqUrlData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.url").isNotEmpty())
                .andExpect(jsonPath("$.data.count").value(2));

        assertThat(urlInfo.getCount()).isEqualTo(2L);
    }

    @Test
    void URL변환실패() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidReqUrlData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(VALIDATION_ERROR.getStatus()));
    }
}
