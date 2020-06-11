package com.task.musinsa.controller;

import com.task.musinsa.data.ApiResponse;
import com.task.musinsa.data.UrlData.RequestUrlData;
import com.task.musinsa.data.UrlData.ResponseUrlData;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.properties.WebProperties;
import com.task.musinsa.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.task.musinsa.data.ApiResponse.ApiStatus.*;

@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    private final UrlValidator urlValidator;
    private final WebProperties webProperties;

    /**
     * 메인 페이지 이동
     * @return : index
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * url을 전달받아 길이를 8자리의 고유의 값으로 변환하여 전달한다.
     * @return ApiResponse 짧게 변환한 url 및 요청 수
     */
    @ResponseBody
    @PostMapping("/convert")
    public ApiResponse convertShortUrl(@RequestBody RequestUrlData urlData, BindingResult bindingResult) {
        if (urlData.getUrl().length() > webProperties.getUrlMaxLength()) return ApiResponse.fail(EXCEED_MAX_LENGTH);
        if (!urlData.isContainSchema()) return ApiResponse.fail(NOT_CONTAIN_SCHEME);
        if (!urlValidator.isValid(urlData.getUrl())) return ApiResponse.fail(VALIDATION_ERROR);

        UrlInfo urlInfo = urlService.getShortUrl(urlData.getUrl());

        return new ResponseUrlData(urlInfo);
   }

    /**
     * 등록된 URL일 경우 고유의 URL을 받아 Redirect 시킨다.
     * url의 길이가 8자
     * @param shortUrl
     * @param model
     * @return index 또는 shortUrl이 나타내느 고유의 URL
     */
    @GetMapping("/{shortUrl}")
    public String findShorUrlAndRedirect(@PathVariable String shortUrl, Model model) {
        if (shortUrl == null || shortUrl.length() > 8) {
            model.addAttribute("error", VALIDATION_ERROR.getMessage());
            return "index";
        }

        Optional<UrlInfo> optUrlInfo = urlService.findShortUrl(shortUrl);

        if (!optUrlInfo.isPresent()) {
            model.addAttribute("error", NOT_EXIST_URL.getMessage());
            return "index";
        }

        return "redirect:" + optUrlInfo.get().getUrl();
    }
}
