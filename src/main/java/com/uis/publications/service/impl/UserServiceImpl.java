package com.uis.publications.service.impl;

import com.uis.publications.dto.UserDTO;
import com.uis.publications.exception.DataNotFoundException;
import com.uis.publications.mappers.UserMapper;
import com.uis.publications.model.Score;
import com.uis.publications.model.User;
import com.uis.publications.repository.IScoreRepository;
import com.uis.publications.repository.IUserRepository;
import com.uis.publications.security.JwtTokenUtil;
import com.uis.publications.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;
    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository= userRepository;
    }

    private IScoreRepository scoreRepository;
    @Autowired
    public void setScoreRepository(IScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDTO getUserDataByToken(String token) {
        String username = null;
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (Exception e) {
                System.out.println("JwtRequestFilter: " + e.getMessage());
            }
        } else {
            System.out.println("JwtRequestFilter: No token found in request header");
        }
        User user = this.userRepository.findTopByUsername(username)
                .orElseThrow((() -> new DataNotFoundException("User not found")));
        List<Score> score=scoreRepository.getScoreByIdUser(user.getId());
        int count=0;
        if(!score.isEmpty()){
            for (Score newscore : score) {

                if (newscore.getIs_positive()) {
                    count = count + 1;
                } else {
                    count = count - 1;
                }
            }
        }
        UserDTO userDTO= UserMapper.INSTANCE.toUserDTO(user);
        userDTO.setScore((long) count);
        return userDTO;
    }
}
