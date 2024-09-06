//package org.backend.bambam_backend.service;
//
//import org.backend.bambam_backend.dto.FeedingDTO;
//import org.backend.bambam_backend.entity.Feeding;
//import org.backend.bambam_backend.repository.FeedingRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FeedingService {
//
//    @Autowired
//    private FeedingRepository feedingRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;  // ModelMapper 주입
//
//    public List<FeedingDTO> getAllFeedings() {
//        List<Feeding> feedingList = feedingRepository.findAll();
//
//        System.out.println("feedingList: " + feedingList);
//
//        return feedingList.stream()
//                .map(feeding -> modelMapper.map(feeding, FeedingDTO.class))  // 자동 변환
//                .collect(Collectors.toList());
//    }
//}