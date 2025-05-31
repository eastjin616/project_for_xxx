package com.tosspayments.exam.reservation.db;

/** !!!!!!! 패키지 구조, 클래스 이름, 생성자 함수를 변경하지 마세요 !!!!!!! */
public class RoomReservation {
    private final TimeSlice time;
    private final long userId;
    private final long roomId;

    public RoomReservation(
        final TimeSlice time,
        final long userId,
        final long roomId
    ) {
        this.time = time;
        this.userId = userId;
        this.roomId = roomId;
    }

    public TimeSlice getTime() {
        return time;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "time=" + time +
                ", userId=" + userId +
                ", roomId=" + roomId +
                '}';
    }
}
