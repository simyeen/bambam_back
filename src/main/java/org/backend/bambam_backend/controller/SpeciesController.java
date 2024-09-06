//package org.backend.bambam_backend.controller;
//
//import org.backend.bambam_backend.dto.SpeciesDTO;
//import org.backend.bambam_backend.service.SpeciesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/species")
//public class SpeciesController {
//
//    @Autowired
//    private SpeciesService speciesService;
//
//    @GetMapping
//    public ResponseEntity<List<SpeciesDTO>> getAllSpecies(){
//        List<SpeciesDTO> speciesDTOList = speciesService.getAllSpecies();
//        System.out.println("speciesDTOList " + speciesDTOList);
//        return ResponseEntity.ok(speciesDTOList);
//    }
//
//}
