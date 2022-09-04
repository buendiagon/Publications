package com.uis.publications.service.impl;

import com.uis.publications.dto.LikeDTO;
import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.mappers.LikeMapper;
import com.uis.publications.mappers.PublicationsMapper;
import com.uis.publications.model.Like;
import com.uis.publications.model.Publication;
import com.uis.publications.repository.ILikeRepository;
import com.uis.publications.repository.IPublicationsRepository;
import com.uis.publications.service.interfaces.ILikeService;
import com.uis.publications.service.interfaces.IPublicationsService;
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
             if(likeList.getId_user().equals(like.getId_user())){
                 create=false;
                 break;
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
    public Boolean deleLikeById(Long id) {
        if(this.likeRepository.existsById(id)){
            this.likeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
