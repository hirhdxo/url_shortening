package com.task.musinsa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
public class UrlInfo extends BaseEntity {
    /**
     * URL
     */
    @Column(length = 2083, unique = true)
    private String url;

    /**
     * 짧게 변한된 URL
     */
    @Column(length = 8, unique = true)
    private String shortUrl;

    /**
     * 요청 회수
     */
    @Column
    private Long count;

    @Transient
    private String serviceUrl;

    /**
     * UrlInfo 객체 생성
     * @param url
     * @param shortUrl
     * @param serviceDomain
     * @return 파라미터 데이터를 포함한 UrlInfo 객체
     */
    public static UrlInfo of(String url, String shortUrl, String serviceDomain) {
        UrlInfo info = new UrlInfo();
        info.shortUrl = shortUrl;
        info.url = url;
        info.count = 1L;
        info.serviceUrl = serviceDomain + info.shortUrl;

        return info;
    }

    /**
     * 요청 시 요청 카운트를 1 증가시킨다.
     */
    private void increaseCount() {
        this.count += 1;
    }

    /**
     * 생성된 shortURl과 서비스 중인 도메인을 결합한다.
     */
    public void updateUrlInfo(String serviceDomain) {
        increaseCount();
        this.serviceUrl = serviceDomain + this.shortUrl;
    }
}
