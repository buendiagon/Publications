package com.uis.publications.mappers;

import com.uis.publications.dto.UserDTO;
import com.uis.publications.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Daniel Adrian Gonzalez Buendia
 */

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapping User to UserDTO
    UserDTO toUserDTO(User user);

    // Mapping UserDTO to User
    User toUser(UserDTO userDTO);
}
