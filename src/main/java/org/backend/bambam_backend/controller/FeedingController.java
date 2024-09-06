//package org.backend.bambam_backend.controller;
//
//import org.backend.bambam_backend.dto.FeedingDTO;
//import org.backend.bambam_backend.service.FeedingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/feeding")
//public class FeedingController {
//
//    @Autowired
//    private FeedingService feedingService;
//
//    @GetMapping
//    public ResponseEntity<List<FeedingDTO>> getAllFeedings(){
//        List<FeedingDTO> feedingDTOList = feedingService.getAllFeedings();
//        System.out.println("feedingDTOList: " + feedingDTOList);
//        return ResponseEntity.ok(feedingDTOList);
//    }
//
//}