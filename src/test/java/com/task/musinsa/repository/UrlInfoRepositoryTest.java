package com.task.musinsa.repository;

import com.task.musinsa.BaseSpringTest;
import com.task.musinsa.domain.UrlInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;

public class UrlInfoRepositoryTest extends BaseSpringTest {
    @Autowired
    private UrlInfoRepository urlInfoRepository;

    private UrlInfo validUrlInfo;

    @BeforeEach
    void init() {
        validUrlInfo = UrlInfo.of(VALID_TEST_URL, VALID_TEST_SHORT_URL, SERVICE_DOMAIN);
    }

    @Test
    void saveVaildUrlInfo() {
        assertThat(validUrlInfo.getId()).isNull();
        assertThat(validUrlInfo.getCount()).isEqualTo(1L);
        urlInfoRepository.save(validUrlInfo);

        assertThat(urlInfoRepository.count()).isEqualTo(1L);
    }

    @Test
    void saveInvalidUrlInfo() {
        UrlInfo invalidUrlInfo = UrlInfo.of(VALID_TEST_URL, INVALID_TEST_SHORT_URL, SERVICE_DOMAIN);
        assertThatThrownBy(()->{
            urlInfoRepository.save(invalidUrlInfo);
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void countByShortUrl() {
        urlInfoRepository.save(validUrlInfo);

        long count = urlInfoRepository.countByShortUrl(validUrlInfo.getShortUrl());
        assertThat(count).isEqualTo(1L);
    }
}
