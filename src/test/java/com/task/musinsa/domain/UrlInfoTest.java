package com.task.musinsa.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlInfoTest {
    private static final String FIRST_TEST_URL = "https://store.musinsa.com/app/?gclid=CjwKCAjw5vz2BRAtEiwAbcVIL16p22BH-qIFoKt2nmGtgSpbwCvlNHdNY0GTrdwXzVsY8J9Qf6ogjRoCaC0QAvD_BwE";
    private static final String FIRST_TEST_SHORT_URL = "12345678";
    private static final String SERVICE_DOMAIN = "http://ktko.tistory.com";

    private UrlInfo urlInfo = UrlInfo.of(FIRST_TEST_URL, FIRST_TEST_SHORT_URL, SERVICE_DOMAIN);

    @Test
    void createUrlInfo() {
        urlInfo = UrlInfo.of(FIRST_TEST_URL, FIRST_TEST_SHORT_URL, SERVICE_DOMAIN);
        assertThat(urlInfo.getCount()).isEqualTo(1L);
    }

    @Test
    void increaseCount() {
        urlInfo.increaseCount();
        assertThat(urlInfo.getCount()).isEqualTo(2L);
    }
}
