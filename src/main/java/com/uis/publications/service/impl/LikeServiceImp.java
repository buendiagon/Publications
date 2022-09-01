package com.uis.publications.service.impl;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.mappers.LikeMapper;
import com.uis.publications.model.Like;
import com.uis.publications.repository.ILikeRepository;
import com.uis.publications.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Juan David Morantes Vergara
 **/
@Service
public class LikeServiceImp implements ILikeService {
    @Autowired
    ILikeRepository likeRepository;
    @Override
    public List<LikeDTO> getLikes() {
        List<Like> geLike = likeRepository.findAll();
        return geLike.stream()
                .map(LikeMapper.INSTANCE::toLikeDTO).collect(Collectors.toList());
        
    }
    @Override
    public LikeDTO createLike(LikeDTO likeDTO) {
        return LikeMapper.INSTANCE.toLikeDTO(
                this.likeRepository.save(LikeMapper.INSTANCE.toLike(likeDTO)));
    }
}
