//package org.backend.bambam_backend.service;
//
//import org.backend.bambam_backend.dto.ReptileDTO;
//import org.backend.bambam_backend.dto.SpeciesDTO;
//import org.backend.bambam_backend.entity.Reptile;
//import org.backend.bambam_backend.entity.Species;
//import org.backend.bambam_backend.repository.ReptileRepository;
//import org.backend.bambam_backend.repository.SpeciesRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ReptileService {
//
//    @Autowired
//    private ReptileRepository reptileRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;  // ModelMapper 주입
//
//    public List<ReptileDTO> getAllReptile() {
//        List<Reptile> reptileList = reptileRepository.findAll();
//        System.out.println("reptileList" + reptileList);
//        return reptileList.stream()
//                .map(reptile -> modelMapper.map(reptile, ReptileDTO.class))  // 자동 변환
//                .collect(Collectors.toList());
//    }
//
//    public ReptileDTO createReptile(ReptileDTO reptileDTO, MultipartFile file) throws IOException {
//        Reptile reptile = modelMapper.map(reptileDTO, Reptile.class);
//        reptile.setImage(file.getBytes());  // 이미지 데이터를 저장
//        Reptile savedReptile = reptileRepository.save(reptile);
//
//        return modelMapper.map(savedReptile, ReptileDTO.class);
//    }
//}
