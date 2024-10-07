package org.backend.bambam_backend.repository;

import org.backend.bambam_backend.entity.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FeedingRepository extends JpaRepository<Feeding, Long> {

    // 1. 몸무게와 먹이 준 날짜 (weight가 null인 데이터 제외)
    @Query("SELECT new map(f.feedDate as feedDate, f.weight as weight) FROM Feeding f WHERE f.reptileId = :reptileId AND f.weight IS NOT NULL ORDER BY f.feedDate")
    List<Map<String, Object>> findWeightAndFeedDateByReptileId(@Param("reptileId") Long reptileId);


    // 2. Food Type별 Count
    @Query("SELECT new map(f.foodType as foodType, COUNT(f.foodType) as count) FROM Feeding f WHERE f.reptileId = :reptileId GROUP BY f.foodType")
    List<Map<String, Object>> findFoodTypeCountByReptileId(@Param("reptileId") Long reptileId);

    // 3. 먹이 준 날짜 리스트
    @Query("SELECT f.feedDate FROM Feeding f WHERE f.reptileId = :reptileId ORDER BY f.feedDate")
    List<String> findFeedDatesByReptileId(@Param("reptileId") Long reptileId);

    // 4. 평균값 계산 (강인함, 먹성, 신속함, 귀여움, 똑똑함)
    @Query("SELECT new map(AVG(f.strength) as strength, AVG(f.appetite) as appetite, AVG(f.agility) as agility, AVG(f.cuteness) as cuteness, AVG(f.intelligence) as intelligence) FROM Feeding f WHERE f.reptileId = :reptileId")
    Map<String, Double> findAttributeAveragesByReptileId(@Param("reptileId") Long reptileId);

    List<Feeding> findByReptileIdOrderByFeedDateDesc(Long reptileId);

    List<Feeding> findAllByOrderByFeedDateDesc();

    @Query("SELECT f FROM Feeding f WHERE f.reptileId = :reptileId AND f.feedingId = :feedingId")
    Optional<Feeding> findByReptileIdAndFeedingId(@Param("reptileId") Long reptileId, @Param("feedingId") Long feedingId);
}

