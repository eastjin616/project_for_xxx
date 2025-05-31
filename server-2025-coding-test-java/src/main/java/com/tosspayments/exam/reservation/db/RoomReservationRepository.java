package com.tosspayments.exam.reservation.db;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tosspayments.utils.TodoUtils.TODO;

/** !!!!!!! 패키지 구조, 클래스 이름을 변경하지 마세요 !!!!!!! */
@Repository
public class RoomReservationRepository {
    private final Map<Long, List<RoomReservation>> db = new ConcurrentHashMap<>();

    public RoomReservation insert(final RoomReservation reservation) {
        /** !!!!!!! 메서드 시그니처를 수정하지 마세요 !!!!!!! */
        db.computeIfAbsent(reservation.getRoomId(), k -> new ArrayList<>()).add(reservation);
        return reservation;
    }

    public List<RoomReservation> findByUserId(final long userId) {
        /** !!!!!!! 메서드 시그니처를 수정하지 마세요 !!!!!!! */
        throw TODO("사용자 별 예약 조회 로직을 구현하세요.");
    }

    public List<RoomReservation> findByRoomId(final long roomId) {
        /** !!!!!!! 메서드 시그니처를 수정하지 마세요 !!!!!!! */
        return db.getOrDefault(roomId, List.of());
    }

    public void clearDbForTest() {
        /** !!!!!!! 해당 메서드는 DB에 대한 테스트가 필요할 때 사용하세요 !!!!!!! */
        db.clear();
    }
}
