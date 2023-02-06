package com.uis.publications.service.interfaces;

import com.uis.publications.dto.CareerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public interface ICareerService {
    List<CareerDTO> getAllCareer();
}
