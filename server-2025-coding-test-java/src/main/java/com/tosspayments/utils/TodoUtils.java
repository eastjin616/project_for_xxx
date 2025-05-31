package com.tosspayments.utils;

/** !!!!!!! 클래스 전체를 절대 수정하지 마세요 !!!!!!! */
public class TodoUtils {
    private TodoUtils() {}

    public static TodoException TODO(final String message) {
        return new TodoException(message);
    }
}
