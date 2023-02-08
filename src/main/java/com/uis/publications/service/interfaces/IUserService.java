package com.uis.publications.service.interfaces;

import com.uis.publications.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public interface IUserService {
    UserDTO getUserDataByToken(String token);
}
