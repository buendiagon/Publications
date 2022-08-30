package com.uis.publications.service.interfaces;

import com.uis.publications.dto.LikeDTO;

import java.util.List;
/**
 * @author Juan David Morantes Vergara
 **/
public interface ILikeService {
    List<LikeDTO> getLikes();

    LikeDTO createLike(LikeDTO likeDTO);


    /*    Long getLikesPub(Long id);*/
}
