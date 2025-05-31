package com.tosspayments.exam

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import java.net.ServerSocket

@SpringBootTest
class ExamApplicationTests {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun setProperties(registry: DynamicPropertyRegistry) {
            // random port
            registry.add("gameserver.port", { ServerSocket(0).use { it.localPort }})
        }
    }

    @Test
    fun contextLoads() {
        /** !!!!!! 해당 클래스 전체를 절대 수정/삭제/이동 금지 !!!!!! */
        /** !!!!!! 해당 테스트는 Spring Boot의 기본 테스트로, 애플리케이션 컨텍스트가 정상적으로 로드되는지 확인하는 용도 !!!!!! */
    }
}
