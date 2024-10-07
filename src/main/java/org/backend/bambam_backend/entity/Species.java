package org.backend.bambam_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
@Table(name = "species")
public class Species {
    @Id
    @Column(name = "species_id")  // DB의 species_id 필드와 매핑
    private Long speciesId;  // 엔티티에서는 카멜 케이스 사용

    @Column(name = "species_name")  // DB의 species_name 필드와 매핑
    private String speciesName;  // 엔티티에서는 카멜 케이스 사용

    private String description;  // 컬럼명과 동일하므로 @Column 생략 가능
}