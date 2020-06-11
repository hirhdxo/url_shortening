package com.task.musinsa.service;

import com.task.musinsa.domain.UrlInfo;

import java.util.Optional;

public interface UrlService {
    /**
     * 입력 받은 URL을 8자리의 짧은 URL로 변환한다.
     * 만약 입력받은 URL정보를 가지고 있다면, 요청카운트를 1 증가시킨다.
     * @param url
     * @return 입력받은 URL정보를 포함하는 UrlInfo를 리턴
     * */
    UrlInfo convertShortUrl(String url);

    /**
     * 8자리로 변환된 shortUrl의 정보를 반환한다.
     * @param url
     * @return 입력받은 URL정보를 포함하는 UrlInfo를 리턴
     */
    Optional<UrlInfo> findShortUrl(String url);
}
