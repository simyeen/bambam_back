//package org.backend.bambam_backend.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "reptile")
//public class Reptile {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "reptile_id")  // DB의 reptile_id 필드와 매핑
//    private Long reptileId;  // 엔티티에서는 카멜 케이스 사용
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")  // 외래 키 user_id 필드와 매핑 (users 테이블과 관계)
//    private User user;  // 주인(브리더) 엔티티와 관계 설정
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "species_id")  // 외래 키 species_id 필드와 매핑 (species 테이블과 관계)
//    private Species species;  // 종(species) 엔티티와 관계 설정
//
//    @Column(name = "name")
//    private String name;  // 파충류의 이름
//
//    @Column(name = "birth_date")
//    private String birthDate;  // 파충류의 생일 또는 나이
//
//    @Column(name = "created_at", insertable = false, updatable = false)
//    private String createdAt;  // 기록 생성 날짜
//
//    @Column(name = "updated_at", insertable = false, updatable = false)
//    private String updatedAt;  // 기록 수정 날짜
//
//    @Lob
//    @Column(name = "image")
//    private byte[] image;  // 이미지 필드 추가
//
//}