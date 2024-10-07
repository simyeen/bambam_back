package org.backend.bambam_backend.service;

import org.backend.bambam_backend.dto.ReptileDTO;
import org.backend.bambam_backend.dto.SpeciesDTO;
import org.backend.bambam_backend.entity.Reptile;
import org.backend.bambam_backend.entity.Species;
import org.backend.bambam_backend.entity.User;
import org.backend.bambam_backend.repository.ReptileRepository;
import org.backend.bambam_backend.repository.SpeciesRepository;
import org.backend.bambam_backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReptileService {

    @Autowired
    private ReptileRepository reptileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private ModelMapper modelMapper;  // ModelMapper 주입

    public boolean deleteReptileById(Long id) {
        if (reptileRepository.existsById(id)) {
            reptileRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Reptile ID를 통해 특정 Reptile 데이터 가져오는 메서드
    public Optional<ReptileDTO> getReptileById(Long id) {
        return reptileRepository.findByReptileId(id)
                .map(reptile -> modelMapper.map(reptile, ReptileDTO.class));
    }

    public List<ReptileDTO> getAllReptile() {
        List<Reptile> reptileList = reptileRepository.findAll();
        return reptileList.stream()
                .map(reptile -> modelMapper.map(reptile, ReptileDTO.class))  // 자동 변환
                .collect(Collectors.toList());
    }

    public ReptileDTO createReptile(ReptileDTO reptileDTO) throws IOException {
        // 새로운 Reptile 객체 생성
        Reptile reptile = new Reptile();

        reptile.setName(reptileDTO.getName());
        reptile.setBirthDate(reptileDTO.getBirthDate());
        reptile.setUserId(reptileDTO.getUserId());
        reptile.setSpeciesId(reptileDTO.getSpeciesId());
        reptile.setImage(reptileDTO.getImage());

        System.out.println("reptileService 실행");

        Reptile savedReptile = reptileRepository.save(reptile);
        return modelMapper.map(savedReptile, ReptileDTO.class);
    }


}
