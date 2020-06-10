package com.task.musinsa.repository;

import com.task.musinsa.domain.UrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlInfoRepository extends JpaRepository<UrlInfo, Long> {
    Optional<UrlInfo> findByUrl(String url);
    Optional<UrlInfo> findByShortUrl(String shortUrl);
}
