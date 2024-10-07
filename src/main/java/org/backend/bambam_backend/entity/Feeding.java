package org.backend.bambam_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "feeding")
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeding_id")  // DB의 feeding_id 필드와 매핑
    private Long feedingId;  // 고유 먹이 기록 ID

    @JoinColumn(name = "reptile_id")  // 외래 키 reptile_id 필드와 매핑
    private Long reptileId;  // 파충류 엔티티와 관계 설정

    @Column(name = "feed_date")
    private String feedDate;  // 먹이를 준 날짜

    @Column(name = "food_type")
    private String foodType;  // 먹이 종류

    @Column(name = "quantity")
    private Integer quantity;  // 먹이 양

    @Column(name = "fed")
    private Boolean fed;  // 먹이 섭취 여부

    @Column(name = "image", columnDefinition = "bytea")  // BYTEA 명시적으로 지정
    private byte[] image;

    @Column(name = "description", nullable = true)  // 새로 추가된 필드
    private String description;  // 설명 필드

    @Column(name = "personality", nullable = true)
    private String personality;  // 성격

    @Column(name = "weight", nullable = true)
    private Long weight;  // 몸무게

    @Column(name = "owner", nullable = true)
    private String owner;  // 반려인

    @Column(name = "strength", nullable = true)
    private Integer strength;  // 강인함

    @Column(name = "appetite", nullable = true)
    private Integer appetite;  // 먹성

    @Column(name = "agility", nullable = true)
    private Integer agility;  // 신속함

    @Column(name = "cuteness", nullable = true)
    private Integer cuteness;  // 귀여움

    @Column(name = "intelligence", nullable = true)
    private Integer intelligence;  // 똑똑함
}