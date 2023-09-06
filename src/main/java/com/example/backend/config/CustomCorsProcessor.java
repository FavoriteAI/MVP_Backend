package com.example.backend.config;

import org.springframework.lang.Nullable;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.cors.reactive.DefaultCorsProcessor;

public class CustomCorsProcessor extends DefaultCorsProcessor {
    @Override
    public boolean process(@Nullable CorsConfiguration config, ServerWebExchange exchange) {
        if (config == null) {
            // config가 null일 때도 프리플라이트 요청을 수락하도록 로직을 변경합니다.
            // 이 예제에서는 config가 null일 때 프리플라이트 요청을 그냥 수락하도록 하였습니다.
            // 필요하다면 추가적인 로직을 여기에 추가할 수 있습니다.
            return true;
        }

        // config가 null이 아닌 경우의 원래 처리 로직을 여기에 유지합니다.
        return super.process(config, exchange);
    }

    @Override
    protected void rejectRequest(ServerHttpResponse response) {
        // 필요한 경우 rejectRequest 메서드의 로직을 여기에 변경할 수 있습니다.
        // 이 예제에서는 기본 로직을 그대로 사용합니다.
        super.rejectRequest(response);
    }
}
