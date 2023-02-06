package com.uis.publications.controller;

import com.uis.publications.dto.CareerDTO;
import com.uis.publications.service.interfaces.ICareerService;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@RestController
@RequestMapping("/api/career")
public class CareerController {

    private ICareerService careerService;

    @Autowired
    @Qualifier("careerServiceImpl")
    public void setareerService(ICareerService careerService) {
        this.careerService= careerService;
    }
    @GetMapping
    public ResponseEntity<List<CareerDTO>> getAllCareer(){
        return ResponseEntity.ok(careerService.getAllCareer());
    }

}
