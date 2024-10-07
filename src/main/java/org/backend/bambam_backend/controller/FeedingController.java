package org.backend.bambam_backend.controller;

import org.backend.bambam_backend.dto.FeedingDTO;
import org.backend.bambam_backend.dto.UserDTO;
import org.backend.bambam_backend.service.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feeding")
public class FeedingController {

    @Autowired
    private FeedingService feedingService;

    @GetMapping("/{reptileId}/fid/{feedingId}")
    public ResponseEntity<FeedingDTO> getFeedingByReptileIdAndFeedingId(
            @PathVariable("reptileId") Long reptileId,
            @PathVariable("feedingId") Long feedingId) {

        Optional<FeedingDTO> feedingDTO = feedingService.getFeedingByReptileIdAndFeedingId (reptileId, feedingId);
        return feedingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    // 특정 Feeding 기록을 가져오는 GET 요청
    @GetMapping("/{id}")
    public ResponseEntity<List<FeedingDTO>> getReptileById(@PathVariable("id") Long id) {
        List<FeedingDTO> feedingDTOList = feedingService.getReptileById(id);
        return ResponseEntity.ok(feedingDTOList);
    }

    // 모든 Feeding 기록을 가져오는 GET 요청
    @GetMapping
    public ResponseEntity<List<FeedingDTO>> getAllFeedings() {
        List<FeedingDTO> feedingDTOList = feedingService.getAllFeedings();
        System.out.println("feedingDTOList: " + feedingDTOList);
        return ResponseEntity.ok(feedingDTOList);
    }

    // 새로운 Feeding 기록을 추가하는 POST 요청
    @PostMapping
    public ResponseEntity<FeedingDTO> createFeeding(
            @RequestPart("reptileId") String reptileIdStr,
            @RequestPart("feedDate") String feedDate,
            @RequestPart("foodType") String foodType,
            @RequestPart("quantity") String quantityStr,
            @RequestPart("fed") String fedStr,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

        // 필요한 값들을 변환
        Long reptileId = Long.parseLong(reptileIdStr);
        Integer quantity = Integer.parseInt(quantityStr);
        Boolean fed = Boolean.parseBoolean(fedStr);

        // FeedingDTO에 값 설정
        FeedingDTO feedingDTO = new FeedingDTO();
        feedingDTO.setReptileId(reptileId);
        feedingDTO.setFeedDate(feedDate);
        feedingDTO.setFoodType(foodType);
        feedingDTO.setQuantity(quantity);
        feedingDTO.setFed(fed);

        System.out.println("feedingDTO: Controller호출 " + feedingDTO);
        // 이미지 파일 처리
        if (imageFile != null && !imageFile.isEmpty()) {
            feedingDTO.setImage(imageFile.getBytes());
            System.out.println("이미지 파일 크기: " + imageFile.getSize());
            System.out.println("이미지 원본 파일명: " + imageFile.getOriginalFilename());
        } else {
            System.out.println("이미지 파일이 전송되지 않았습니다.");
        }

        // Feeding 서비스 호출
        FeedingDTO createdFeeding = feedingService.createFeeding(feedingDTO);
        return ResponseEntity.ok(createdFeeding);
    }
}