package com.task.musinsa.controller;

import com.task.musinsa.data.ApiResponse;
import com.task.musinsa.data.UrlData;
import com.task.musinsa.data.UrlData.RequestUrlData;
import com.task.musinsa.data.UrlData.ResponseUrlData;
import com.task.musinsa.domain.UrlInfo;
import com.task.musinsa.service.UrlService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.task.musinsa.data.ApiResponse.ApiStatus.*;

@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    private final UrlValidator urlValidator;

    /**
     * 메인 페이지 이동
     * @return : index Main page
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * url을 전달받아 길이를 8자리의 고유의 값으로 변환하여 전달한다.
     * @return
     */
    @ResponseBody
    @PostMapping("/convert")
    public ApiResponse generateShortUrl(@RequestBody RequestUrlData urlData) {
        if (!urlData.isContainSchema()) return ApiResponse.fail(NOT_CONTAIN_SCHEME);
        if (!urlValidator.isValid(urlData.getUrl())) return ApiResponse.fail(VALIDATION_ERROR);

        UrlInfo urlInfo = urlService.convertShortUrl(urlData.getUrl());

        return new ResponseUrlData(urlInfo);
   }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl, Model model) {
        if (shortUrl == null || shortUrl.length() != 8) {
            model.addAttribute("error", ApiResponse.fail(VALIDATION_ERROR));

            return "index";
        }

        Optional<UrlInfo> optUrlInfo = urlService.findShortUrl(shortUrl);
        if (!optUrlInfo.isPresent()) {
            model.addAttribute("error", ApiResponse.fail(NOT_EXIST_URL));

            return "index";
        }

        return "redirect:" + optUrlInfo.get().getUrl();
    }
}
