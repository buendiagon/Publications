package com.uis.publications.service.interfaces;

import com.uis.publications.dto.LikeDTO;

/**
 * @author Juan David Morantes Vergara
 **/
public interface ILikeService {
    int getLikesByIdPublication(Long idPublication);

    Boolean createLike(LikeDTO likeDTO);

    Boolean deleteLikeByLikeDTO(Long idPublicacion, Long idUser);


    /*    Long getLikesPub(Long id);*/
}
