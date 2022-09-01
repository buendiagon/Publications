package com.uis.publications.service.interfaces;

import com.uis.publications.dto.LikeDTO;

import java.util.List;
/**
 * @author Juan David Morantes Vergara
 **/
public interface ILikeService {
    List<LikeDTO> getLikes();

    Boolean createLike(LikeDTO likeDTO);

    Boolean deleLikeById(Long id);


    /*    Long getLikesPub(Long id);*/
}
