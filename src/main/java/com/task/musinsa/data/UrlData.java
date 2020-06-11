package com.task.musinsa.data;

import com.task.musinsa.domain.UrlInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.net.MalformedURLException;
import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlData {
    private Long count;
    private String url;

    public static UrlData apply(UrlInfo urlInfo) {
        return new UrlData(urlInfo.getCount(), urlInfo.getServiceUrl());
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestUrlData {
        String url;

        public boolean isContainSchema() {
            try {
                URL url = new URL(this.url);
                url.getProtocol();
            } catch (MalformedURLException e) {
                return false;
            }

            return true;
        }
    }

    public static class ResponseUrlData extends ApiResponse<UrlData> {
        public ResponseUrlData(UrlInfo urlInfo) {
            super(ApiStatus.SUCCEEDED.getStatus(), UrlData.apply(urlInfo), ApiStatus.SUCCEEDED.getMessage());
        }
    }
}