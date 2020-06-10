package com.task.musinsa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
public class UrlInfo extends BaseEntity {
    @Column(unique = true)
    private String url;

    @Column(length = 8, unique = true)
    private String shortUrl;

    @Column
    private Long count;

    @Transient
    private String serviceUrl;

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
    public void increaseCount() {
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
