package com.example.demo.constant;

import lombok.Data;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonEnums {

    public static enum SampleStatus {
        未完成(0),
        未发布(1),
        已发布(2);

        private int value;
        SampleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    };
}
