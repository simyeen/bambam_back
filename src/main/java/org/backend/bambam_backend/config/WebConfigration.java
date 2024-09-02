package org.backend.bambam_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigration implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                        .allowedOriginPatterns("*") // 허용할 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // 허용할 HTTP 메서드
                        .allowedHeaders("*") // 허용할 헤더
                        .exposedHeaders("Access_Token", "Refresh_Token","Web") // CORS Header 문제
                        .allowCredentials(true) // 자격 증명(쿠키 등)을 허용할지 여부
                        .maxAge(3600); // 프리플라이트 요청의 캐시 시간을 초 단위로 설정
            }
        };
    }
}