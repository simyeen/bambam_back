package org.backend.bambam_backend.controller;

import org.backend.bambam_backend.service.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statis")
public class StatisticsController {

//@GetMapping('/weight') -> feed_date랑 그때의 몸무게 얻고싶음 -> 일렬로 쭉 확인
// @GetMapping('/foodType') -> 파이차트로 food_type 뭐 주는지 확인하고 싶으니까 어떤 food_type이랑 그거 count 필요함
// @GetMapping('/feedDate') -> 먹이 준날 들 달력에 표시로 확인 ok -> 이 날짜만 뽑아서 주는 API필요
// @GetMapping('/weight') -> 각각의 strength, appetite, agility, cuteness, intelligence의 평균값들을 알고싶음
// 이 데이터들은 모두 retile_id를 인자로 받는 API임

    @Autowired
    FeedingService feedingService;

    @GetMapping("/weight/{id}")
    public ResponseEntity<List<Map<String, Object>>> getWeightAndFeedDate(@PathVariable("id") Long reptileId) {
        List<Map<String, Object>> result = feedingService.getWeightAndFeedDate(reptileId);
        return ResponseEntity.ok(result);
    }

    // 3.
    @GetMapping("/foodType/{id}")
    public ResponseEntity<List<Map<String, Object>>> getFoodTypeCount(@PathVariable("id") Long reptileId) {
        List<Map<String, Object>> result = feedingService.getFoodTypeCount(reptileId);
        return ResponseEntity.ok(result);
    }

    // 4. 달력에 이용하기
    @GetMapping("/feedDate/{id}")
    public ResponseEntity<List<String>> getFeedDates(@PathVariable("id") Long reptileId) {
        List<String> feedDates = feedingService.getFeedDates(reptileId);
        return ResponseEntity.ok(feedDates);
    }

    // 5. radar에 이용하기
    @GetMapping("/averages/{id}")
    public ResponseEntity<Map<String, Double>> getAttributeAverages(@PathVariable("id") Long reptileId) {
        Map<String, Double> averages = feedingService.getAttributeAverages(reptileId);
        return ResponseEntity.ok(averages);
    }

}
