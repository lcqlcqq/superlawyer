package com.lcq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }

    public static Result fail(ErrorCode errorCode) {
        return new Result(false, errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static Result unauthorized(String message) {
        return new Result(false, 403, message, null);
    }

    public static Result forbidden(String message) {
        return new Result(false, 403, message, null);
    }
}
