package org.backend.bambam_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reptile")
public class Reptile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reptile_id")  // DB의 reptile_id 필드와 매핑
    private Long reptileId;  // 엔티티에서는 카멜 케이스 사용

    @Column(name = "user_id", nullable = false)
    private Long userId;  // 외래 키: user_id (User 엔티티 대신 userId 필드 사용)

    @Column(name = "species_id")
    private Long speciesId;  // 외래 키: species_id (Species 엔티티 대신 speciesId 필드 사용)

    @Column(name = "name")
    private String name;  // 파충류의 이름

    @Column(name = "birth_date")
    private String birthDate;  // 파충류의 생일 또는 나이

    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;  // 기록 생성 날짜

    @Column(name = "updated_at", insertable = false, updatable = false)
    private String updatedAt;  // 기록 수정 날짜

    @Column(name = "image", columnDefinition = "bytea")  // BYTEA 명시적으로 지정
    private byte[] image;

}