//package org.backend.bambam_backend.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@Table(name = "feeding")
//public class Feeding {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "feeding_id")  // DB의 feeding_id 필드와 매핑
//    private Long feedingId;  // 고유 먹이 기록 ID
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reptile_id")  // 외래 키 reptile_id 필드와 매핑
//    private Reptile reptile;  // 파충류 엔티티와 관계 설정
//
//    @Column(name = "feed_date")
//    private LocalDateTime feedDate;  // 먹이를 준 날짜
//
//    @Column(name = "food_type")
//    private String foodType;  // 먹이 종류
//
//    @Column(name = "quantity")
//    private Integer quantity;  // 먹이 양
//
//    @Column(name = "fed")
//    private Boolean fed;  // 먹이 섭취 여부
//}