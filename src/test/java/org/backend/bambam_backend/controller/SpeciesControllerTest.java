package org.backend.bambam_backend.controller;

import org.backend.bambam_backend.dto.SpeciesDTO;
import org.backend.bambam_backend.service.SpeciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SpeciesController.class)
public class SpeciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SpeciesService speciesService;

    @InjectMocks
    private SpeciesController speciesController;

    @BeforeEach
    void setUp() {
        // Mockito 초기화
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(speciesController).build();
    }

    @Test
    public void testGetAllSpecies() throws Exception {
        // Mock 데이터를 설정
        SpeciesDTO species1 = new SpeciesDTO();
        species1.setSpeciesName("Python");
        species1.setDescription("Large snake");

        SpeciesDTO species2 = new SpeciesDTO();
        species2.setSpeciesName("Boa Constrictor");
        species2.setDescription("Constrictor snake");

        List<SpeciesDTO> speciesList = Arrays.asList(species1, species2);

        // 서비스의 동작을 모의(Mock)로 설정
        when(speciesService.getAllSpecies()).thenReturn(speciesList);

        // GET 요청을 보내고, 응답을 확인
        mockMvc.perform(get("/api/species")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // HTTP 상태 코드 200 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].speciesName").value("Python"))
                .andExpect(jsonPath("$[1].speciesName").value("Boa Constrictor"));
    }
}