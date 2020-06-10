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
        UNKNOWN(-1, "알 수 없는 에러"),
        SUCCEEDED(0, "정상"),
        NOT_CONTAIN_SCHEME(1, "http 또는 https를 포함하여 입력해 주세요."),
        VALIDATION_ERROR(2, "잘못된 주소 URL입니다. 입력한 주소를 확인해 주세요."),
        NOT_EXIST_URL(3, "존재하지 않은 URL입니다. 입력한 주소를 확인해 주세요.");

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
