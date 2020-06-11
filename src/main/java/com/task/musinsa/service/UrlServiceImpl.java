package com.task.musinsa.service;

import com.google.common.hash.Hashing;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.properties.Properties;
import com.task.musinsa.repository.UrlInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlServiceImpl implements UrlService {
    private final UrlInfoRepository urlInfoRepository;
    private final Properties properties;

    @Override
    @Transactional
    public UrlInfo getShortUrl(String url) {
        Optional<UrlInfo> optUrlInfo = urlInfoRepository.findByUrl(url);

        UrlInfo urlInfo = null;
        if (optUrlInfo.isPresent()) {
            urlInfo = optUrlInfo.get();
            urlInfo.updateUrlInfo(properties.getDomain());
        } else {
            urlInfo = UrlInfo.of(url, convertShortUrl(url), properties.getDomain());
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
     * 만약 중복된 ShortUrl이 생성되었을 경우, 새로운 URL을 생성한다.
     * 중복되었으면 중복된 Hash값을 다시 Hashing 한다.3번 반복하여 중복된 해쉬 값이 나온다면, 자리 수를 줄인다.
     * @param url
     * @return
     */
    private String convertShortUrl(String url) {
        String shortUrl = generateHashValue(url);
        int tryCount = 1;

        while(urlInfoRepository.countByShortUrl(shortUrl) != 0) {
            tryCount += 1;

            if (tryCount == 3) {
                shortUrl = shortUrl.substring(1, shortUrl.length());
                break;
            }

            shortUrl = generateHashValue(shortUrl);
        }

        return shortUrl;
    }

    private String generateHashValue(String value) {
        return Hashing.murmur3_32().hashString(value, Charset.defaultCharset()).toString();
    }
}
