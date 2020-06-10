package com.task.musinsa.service;

import com.task.musinsa.domain.UrlInfo;

import java.util.Optional;

public interface UrlService {
    /**
     * 입력받은 URL의 길이를 줄인다.
     * @return 변환된 길이의 URL 정보를 반환한다.
     */
    UrlInfo convertShortUrl(String url);

    /**
     * URl 정보를 조회한 후 반환한다.
     * @param url
     * @return
     */
    Optional<UrlInfo> findShortUrl(String url);
}
