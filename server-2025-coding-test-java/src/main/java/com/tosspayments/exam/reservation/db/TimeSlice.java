package com.tosspayments.exam.reservation.db;

import java.time.LocalTime;

/** !!!!!!! 패키지 구조, 클래스 이름, 생성자 함수를 변경하지 마세요 !!!!!!! */
public class TimeSlice {
    private final LocalTime start;
    private final LocalTime end;

    public TimeSlice(final LocalTime start, final LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}
