package com.uis.publications.service.impl;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.mappers.LikeMapper;
import com.uis.publications.model.Like;
import com.uis.publications.repository.ILikeRepository;
import com.uis.publications.repository.IUserRepository;
import com.uis.publications.service.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Morantes Vergara
 **/
@Service
public class LikeServiceImp implements ILikeService {
    @Autowired
    ILikeRepository likeRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public int getLikesByIdPublication(Long idPublication) {
        List<Like> Likes = likeRepository.findAll();
        int sumLikes=0;
       for(Like like:Likes){
           if(like.getId_comment()==null){
               if(like.getId_new().equals(idPublication)){
                    sumLikes=sumLikes+1;
               }
           }
       }
       return sumLikes;
    }
    @Override
    public Boolean createLike(LikeDTO likeDTO) {
         Like like=LikeMapper.INSTANCE.toLike(likeDTO);
         List<Like> likes=likeRepository.findAll();
         boolean create=true;
         for(Like likeList:likes){
             if(likeDTO.getId_new().equals(likeList.getId_new())){
                 if(likeList.getId_user().equals(like.getId_user())){
                     create=false;
                 }
             }
         }
        if(create){
            this.likeRepository.save(like);
            return true;
        }else{

         return false;
        }
    }

    @Override
    public Boolean deleteLikeByLikeDTO(Long idPublicacion, Long idUser) {
        List<Like> likes=likeRepository.findAll();
        boolean isOk=false;
        for(Like like:likes){
            if(idUser.equals(like.getId_user()) && idPublicacion.equals(like.getId_new())){
                this.likeRepository.deleteById(like.getId());
                isOk=true;
                break;
            }
        }
        return isOk;
    }

}
