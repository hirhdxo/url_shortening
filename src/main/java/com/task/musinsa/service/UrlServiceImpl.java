package com.task.musinsa.service;

import com.google.common.hash.Hashing;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.repository.UrlInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlServiceImpl implements UrlService {
    private final UrlInfoRepository urlInfoRepository;

    @Value("${service.domain}")
    private String serviceDomain;

    @Override
    @Transactional
    public UrlInfo convertShortUrl(String url) {
        Optional<UrlInfo> optUrlInfo = urlInfoRepository.findByUrl(url);

        UrlInfo urlInfo = null;
        if (optUrlInfo.isPresent()) {
            urlInfo = optUrlInfo.get();
            urlInfo.updateUrlInfo(serviceDomain);
        } else {
            urlInfo = UrlInfo.of(url, generateShortUrl(url), serviceDomain);
            urlInfoRepository.save(urlInfo);
        }

        return urlInfo;
    }

    @Override
    public Optional<UrlInfo> findShortUrl(String shortUrl) {

        return urlInfoRepository.findByShortUrl(shortUrl);
    }

    /**
     * 해당 URL에 대한 shortUrl을 생성한다.
     * @param url
     * @return
     */
    private String generateShortUrl(String url) {
        return Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
    }
}
