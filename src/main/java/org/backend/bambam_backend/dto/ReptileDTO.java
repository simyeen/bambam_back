package org.backend.bambam_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReptileDTO {
    private Long reptileId;
    private Long userId;  // User의 ID를 사용
    private Long speciesId;  // Species의 ID를 사용
    private String name;
    private String birthDate;
    private byte[] image;  // 이미지 데이터
}