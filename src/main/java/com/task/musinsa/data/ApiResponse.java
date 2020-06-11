package com.task.musinsa.data;

import com.task.musinsa.domain.UrlInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private T data;
    private String message;

    @Getter
    public enum ApiStatus {
        UNKNOWN(-1, "Unknown Error"),
        SUCCEEDED(0, "SUCCEEDED"),
        NOT_CONTAIN_SCHEME(1, "Please include http or https."),
        VALIDATION_ERROR(2, "Invalid address. Please check the address you entered."),
        NOT_EXIST_URL(3, "Not exist address. Please check the address you entered."),
        EXCEED_MAX_LENGTH(4, "exceed max length. The address length is less than 2083.");

        private int status;
        private String message;

        ApiStatus(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    public static ApiResponse fail(ApiStatus apiStatus) {
        return new ApiResponse(apiStatus.status, null, apiStatus.message);
    }
}
