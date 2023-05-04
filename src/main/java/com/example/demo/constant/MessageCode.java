package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

    SUCC_0(0, ""),

    UNFINISHEDSAMPLE_10001(10001, "请完成样片信息");

    private int code;
    private String message;

};
