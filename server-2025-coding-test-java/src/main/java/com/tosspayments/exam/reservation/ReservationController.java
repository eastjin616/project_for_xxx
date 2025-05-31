package com.tosspayments.exam.reservation;

import com.tosspayments.exam.reservation.db.RoomReservation;
import com.tosspayments.exam.reservation.db.RoomReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tosspayments.utils.TodoUtils.TODO;

@RestController
public class ReservationController {
    private final RoomReservationRepository roomReservationRepository;

    public ReservationController(final RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }

    /** !!!!!!! API 스펙(HTTP 메서드, URL(PathVariable 포함), Request(Header, Param, Body) 구조, Response(Header, Param, Body))구조는 절대 수정하지 마세요 !!!!!!! */
    /** !!!!!!! 추가적인 패키지나 클래스가 필요하다면 자유롭게 작성해도 됩니다. !!!!!!! */
    
    @PostMapping("/api/reservations")
    public void createReservation(@RequestBody final RoomReservationRequestDto request) {
        throw TODO("회의실 예약 로직을 구현하세요.");
    }

    @GetMapping("/api/reservations/users/{userId}")
    public List<RoomReservation> getUserReservations(@PathVariable final long userId) {
        throw TODO("사용자 별 예약 조회 로직을 구현하세요.");
    }

    @GetMapping("/api/reservations/rooms/{roomId}")
    public List<RoomReservation> getRoomReservations(@PathVariable final long roomId) {
        /** !!!!!!! 해당 API는 절대 수정하지 마세요 !!!!!!! */
        /** !!!!!!! 해당 회의실의 예약 현황을 조회 하기 위한 코드이니 수정하지 마세요 !!!!!!! */
        return roomReservationRepository.findByRoomId(roomId);
    }
}
