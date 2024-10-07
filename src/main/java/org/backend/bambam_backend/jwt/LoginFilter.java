package org.backend.bambam_backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.backend.bambam_backend.dto.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import java.io.InputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        System.out.println("LoginFilter 생성자실행");
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String password = null;

        // 클라이언트 요청이 JSON인 경우 수동으로 파싱
        if ("application/json".equals(request.getContentType())) {
            try {
                // JSON 데이터를 파싱하여 username과 password 추출
                InputStream inputStream = request.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> credentials = objectMapper.readValue(inputStream, Map.class);

                username = credentials.get("username");
                password = credentials.get("password");

            } catch (IOException e) {
                throw new AuthenticationServiceException("Request content parsing failed", e);
            }
        } else {
            // form-data인 경우 기본 메서드를 사용해 추출
            username = obtainUsername(request);
            password = obtainPassword(request);
        }

        // username과 password가 null이면 예외 처리
        if (username == null || password == null) {
            throw new AuthenticationServiceException("Username or password is empty");
        }

        // 스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        System.out.println("attemptAuthentication 인증필터 작동: " + username + " " + password);
        // token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("successfulAuthentication 로그인 인증 성공!");

        //UserDetailsS
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60*60*10000L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("unsuccessfulAuthentication 인증 실패!" + request);

        response.setStatus(401);
    }
}
