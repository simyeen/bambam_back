package org.backend.bambam_backend.controller;

import org.backend.bambam_backend.dto.ReptileDTO;
import org.backend.bambam_backend.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reptile")
public class ReptileController {

    @Autowired
    private ReptileService reptileService;

    // Reptile ID를 통해 특정 Reptile 삭제하는 메서드
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReptileById(@PathVariable("id") Long id) {
        boolean isDeleted = reptileService.deleteReptileById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();  // 200 OK
        } else {
            return ResponseEntity.status(404).build();  // 404 Not Found
        }
    }

    // Reptile ID를 통해 특정 Reptile 가져오는 메서드
    @GetMapping("/{id}")
    public ResponseEntity<ReptileDTO> getReptileById(@PathVariable("id") Long id) {
        Optional<ReptileDTO> reptileDTO = reptileService.getReptileById(id);

        return reptileDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping
    public ResponseEntity<List<ReptileDTO>> getAllReptiles() {
        List<ReptileDTO> reptileDTOList = reptileService.getAllReptile();
        System.out.println("reptileDTOList 요청실행" );
        return ResponseEntity.ok(reptileDTOList);
    }


    @PostMapping
    public ResponseEntity<ReptileDTO> createReptile(
            @RequestPart("name") String name,
            @RequestPart("birthDate") String birthDate,
            @RequestPart("userId") String userIdStr,  // 문자열로 받음
            @RequestPart("speciesId") String speciesIdStr,  // 문자열로 받음
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

        // 문자열로 받은 userId와 speciesId를 Long으로 변환
        Long userId = Long.parseLong(userIdStr);
        Long speciesId = Long.parseLong(speciesIdStr);

        // ReptileDTO에 값 설정
        ReptileDTO reptileDTO = new ReptileDTO();
        reptileDTO.setName(name);
        reptileDTO.setBirthDate(birthDate);
        reptileDTO.setUserId(userId);
        reptileDTO.setSpeciesId(speciesId);  // speciesId 설정
        reptileDTO.setImage(imageFile.getBytes());


        System.out.println("----------------------------------------");
        System.out.println("Contoller 실행");
//        System.out.println("imageFile 이미지확인" + imageFile);

        // 이미지 파일의 크기와 원본 파일명을 출력하여 파일이 제대로 들어왔는지 확인
        if (imageFile != null) {
            System.out.println("이미지 파일 크기: " + imageFile.getSize());  // 파일 크기 출력
            System.out.println("이미지 원본 파일명: " + imageFile.getOriginalFilename());  // 파일명 출력
        } else {
            System.out.println("이미지 파일이 전송되지 않았습니다.");
        }

        System.out.println("----------------------------------------");

        // Reptile 서비스 호출
        ReptileDTO createdReptile = reptileService.createReptile(reptileDTO);
        return ResponseEntity.ok(createdReptile);
    }
}
// 이제 프론트에서 한 번 랩타일 생성, 삭제,