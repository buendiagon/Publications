package com.uis.publications.service.impl;

import com.uis.publications.dto.CareerDTO;
import com.uis.publications.exception.DataNotFoundException;
import com.uis.publications.mappers.CareerMapper;
import com.uis.publications.model.Career;
import com.uis.publications.repository.ICareerRepository;
import com.uis.publications.service.interfaces.ICareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public class CareerServiceImpl implements ICareerService {

    private ICareerRepository careerRepository;
    @Autowired
    public void setCareerRepository(ICareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }


    @Override
    public List<CareerDTO> getAllCareer() {
        List<Career> listCareer =careerRepository.findAll();
        return listCareer.stream()
                .map(CareerMapper.INSTANCE::toCarrerDTO).collect(Collectors.toList());
    }
}
