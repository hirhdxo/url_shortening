package com.task.musinsa.service;

import com.task.musinsa.BaseSpringTest;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.repository.UrlInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlServiceTest extends BaseSpringTest {
    @Autowired
    UrlService urlService;
    @Autowired
    UrlInfoRepository urlInfoRepository;

    private static final String NOT_EXIST_SHORT_URL = "theo.kko";

    @Test
    void convertShortUrlTest() {
        UrlInfo urlInfo = urlService.convertShortUrl(VALID_TEST_URL);
        System.out.println(urlInfo.getId());
        assertThat(urlInfo.getId()).isEqualTo(1L);
        assertThat(urlInfo.getCount()).isEqualTo(1L);
        assertThat(urlInfo.getShortUrl().length()).isEqualTo(8);

        urlService.convertShortUrl(VALID_TEST_URL);

        assertThat(urlInfo.getCount()).isEqualTo(2L);
    }

    @Test
    void findShortUrl() {
        UrlInfo initUrlInfo = urlService.convertShortUrl(VALID_TEST_URL);
        String shortUrl = initUrlInfo.getShortUrl();

        Optional<UrlInfo> optUrlInfo = urlService.findShortUrl(shortUrl);
        UrlInfo urlInfo = optUrlInfo.get();
        assertThat(optUrlInfo.isPresent()).isTrue();
        assertThat(urlInfo.getUrl()).isEqualTo(VALID_TEST_URL);
        assertThat(urlInfo.getShortUrl().length()).isEqualTo(8);
        assertThat(urlInfo.getCount()).isEqualTo(1L);
    }
}
