package org.backend.bambam_backend.service;

import org.backend.bambam_backend.dto.SpeciesDTO;
import org.backend.bambam_backend.entity.Species;
import org.backend.bambam_backend.repository.SpeciesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private ModelMapper modelMapper;  // ModelMapper 주입

    public List<SpeciesDTO> getAllSpecies() {
        List<Species> speciesList = speciesRepository.findAll();

        System.out.println("speciesList" + speciesList);

        return speciesList.stream()
                .map(species -> modelMapper.map(species, SpeciesDTO.class))  // 자동 변환
                .collect(Collectors.toList());
    }
}
