package org.backend.bambam_backend.service;

import org.backend.bambam_backend.dto.SpeciesDTO;
import org.backend.bambam_backend.entity.Species;
import org.backend.bambam_backend.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SpeciesServiceTest {

    @InjectMocks
    private SpeciesService speciesService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        // Mockito의 모의 객체를 초기화
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSpecies() {
        // 목 데이터 생성
        List<Species> mockSpeciesList = new ArrayList<>();
        Species species1 = new Species();
        species1.setSpeciesId(1L); // 스네이크 케이스 필드 사용
        species1.setSpeciesName("Python");
        species1.setDescription("Large snake");

        mockSpeciesList.add(species1);

        // 모의 객체 동작 설정
        when(speciesRepository.findAll()).thenReturn(mockSpeciesList);

        // DTO로 변환할 때의 동작 설정
        SpeciesDTO speciesDTO = new SpeciesDTO();
        speciesDTO.setSpeciesName("Python");
        speciesDTO.setDescription("Large snake");

        when(modelMapper.map(species1, SpeciesDTO.class)).thenReturn(speciesDTO);

        // Service의 getAllSpecies 메서드 실행
        List<SpeciesDTO> result = speciesService.getAllSpecies();

        // 결과 확인
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Python", result.get(0).getSpeciesName());
    }
}