package org.backend.bambam_backend.service;

import org.backend.bambam_backend.dto.FeedingDTO;
import org.backend.bambam_backend.entity.Feeding;
import org.backend.bambam_backend.entity.Reptile;
import org.backend.bambam_backend.repository.FeedingRepository;
import org.backend.bambam_backend.repository.ReptileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedingService {

    @Autowired
    private FeedingRepository feedingRepository;

    @Autowired
    private ReptileRepository reptileRepository;

    @Autowired
    private ModelMapper modelMapper;  // ModelMapper 주입

    // 1. 몸무게와 먹이 준 날짜
    public List<Map<String, Object>> getWeightAndFeedDate(Long reptileId) {
        return feedingRepository.findWeightAndFeedDateByReptileId(reptileId);
    }

    // 2. Food Type별 Count
    public List<Map<String, Object>> getFoodTypeCount(Long reptileId) {
        return feedingRepository.findFoodTypeCountByReptileId(reptileId);
    }

    // 3. 먹이 준 날짜 리스트
    public List<String> getFeedDates(Long reptileId) {
        return feedingRepository.findFeedDatesByReptileId(reptileId);
    }

    // 4. 평균값 계산
    public Map<String, Double> getAttributeAverages(Long reptileId) {
        return feedingRepository.findAttributeAveragesByReptileId(reptileId);
    }


    public Optional<FeedingDTO> getFeedingByReptileIdAndFeedingId(Long reptileId, Long feedingId) {
        return feedingRepository.findByReptileIdAndFeedingId(reptileId, feedingId)
                .map(feeding -> modelMapper.map(feeding, FeedingDTO.class));  // ModelMapper로 변환
    }

    // 특정 Feeding 기록을 ID로 가져오는 메서드
    public List<FeedingDTO> getReptileById(Long reptileId) {
        List<Feeding> feedingList = feedingRepository.findByReptileIdOrderByFeedDateDesc(reptileId);

        // Feeding 리스트에서 첫 번째 값을 Optional로 반환
        return feedingList.stream()
                .map(feeding -> modelMapper.map(feeding, FeedingDTO.class))  // 자동 변환
                .collect(Collectors.toList());
    }

    // 모든 Feeding 기록을 가져오는 메서드
    public List<FeedingDTO> getAllFeedings() {
        List<Feeding> feedingList = feedingRepository.findAllByOrderByFeedDateDesc();

        System.out.println("feedingList: " + feedingList);

        return feedingList.stream()
                .map(feeding -> modelMapper.map(feeding, FeedingDTO.class))  // 자동 변환
                .collect(Collectors.toList());
    }

    // 새로운 Feeding 기록을 생성하는 메서드
    public FeedingDTO createFeeding(FeedingDTO feedingDTO) throws IOException {
        // Reptile이 존재하는지 확인
        Optional<Reptile> reptileOpt = reptileRepository.findById(feedingDTO.getReptileId());
        if (reptileOpt.isEmpty()) {
            throw new RuntimeException("Reptile not found with ID: " + feedingDTO.getReptileId());
        }

        // 새로운 Feeding 객체 생성
        Feeding feeding = new Feeding();
        feeding.setReptileId(feedingDTO.getReptileId());  // Reptile 설정
        feeding.setFeedDate(feedingDTO.getFeedDate());  // 먹이 날짜 설정
        feeding.setFoodType(feedingDTO.getFoodType());  // 먹이 종류 설정
        feeding.setQuantity(feedingDTO.getQuantity());  // 먹이 양 설정
        feeding.setFed(feedingDTO.getFed());  // 먹이 섭취 여부 설정
        feeding.setImage(feedingDTO.getImage());  // 이미지 설정 (선택적)

        // Feeding 객체 저장
        Feeding savedFeeding = feedingRepository.save(feeding);

        // 저장된 Feeding 객체를 DTO로 변환하여 반환
        return modelMapper.map(savedFeeding, FeedingDTO.class);
    }
}