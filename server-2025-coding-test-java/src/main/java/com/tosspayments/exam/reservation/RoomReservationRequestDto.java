package com.tosspayments.exam.reservation;

public class RoomReservationRequestDto {
    private final long roomId;
    private final long userId;
    private final String from;
    private final String to;

    public RoomReservationRequestDto(
        final long roomId,
        final long userId,
        final String from,
        final String to
    ) {
        this.roomId = roomId;
        this.userId = userId;
        this.from = from;
        this.to = to;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getUserId() {
        return userId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
