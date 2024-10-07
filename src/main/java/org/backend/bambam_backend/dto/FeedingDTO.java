package org.backend.bambam_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedingDTO {

    private Long feedingId;  // 고유 먹이 기록 ID
    private Long reptileId;  // 파충류 ID
    private String feedDate;  // 먹이를 준 날짜
    private String foodType;  // 먹이 종류
    private Integer quantity;  // 먹이 양
    private Boolean fed;  // 먹이 섭취 여부
    private byte[] image;  // 이미지 데이터
    private String description;  // 설명 필드
    private String personality;  // 성격
    private Long weight;  // 몸무게
    private String owner;  // 반려인
    private Integer strength;  // 강인함
    private Integer appetite;  // 먹성
    private Integer agility;  // 신속함
    private Integer cuteness;  // 귀여움
    private Integer intelligence;  // 똑똑함
}