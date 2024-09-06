//package org.backend.bambam_backend.controller;
//
//import org.backend.bambam_backend.dto.ReptileDTO;
//import org.backend.bambam_backend.service.ReptileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/reptile")
//public class ReptileController {
//
//    @Autowired
//    private ReptileService reptileService;
//
//    @GetMapping
//    public ResponseEntity<List<ReptileDTO>> getAllReptiles() {
//        List<ReptileDTO> reptileDTOList = reptileService.getAllReptile();
//        System.out.println("reptileDTOList: " + reptileDTOList);
//        return ResponseEntity.ok(reptileDTOList);
//    }
//
//    @PostMapping
//    public ResponseEntity<ReptileDTO> createReptile(
//            @RequestPart("reptile") ReptileDTO reptileDTO,
//            @RequestPart("image") MultipartFile imageFile) throws IOException {
//
//        ReptileDTO createdReptile = reptileService.createReptile(reptileDTO, imageFile);
//        return ResponseEntity.ok(createdReptile);
//    }
//}
//// 이제 프론트에서 한 번 랩타일 생성, 삭제,