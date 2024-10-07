package org.backend.bambam_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesDTO {
    private Long speciesId;
    private String speciesName;
    private String description;
}