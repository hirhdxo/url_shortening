package com.task.musinsa.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "service")
public class Properties {
    private String domain;

    /**
     * 모든 브라우저 마다 허용하는 URL의 길이는 다르다.
     * 확인해본 결과 IE 브라우저의 길이가 제일 적다. 2,083자
     * https://www.quora.com/How-long-can-a-URL-be
     */
    private int urlMaxLength;
}
