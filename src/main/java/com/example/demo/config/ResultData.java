package com.example.demo.config;

import com.example.demo.constant.MessageCode;
import lombok.Data;

@Data
public class ResultData<T> {
    private int code;

    private String message;

    private T data;

    private Long timestamp;

    public ResultData () {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(MessageCode.SUCC_0.getCode());
        resultData.setMessage(MessageCode.SUCC_0.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(1);
        resultData.setMessage(message);
        return resultData;
    }

}
