package com.uis.publications.service.impl;

import com.uis.publications.model.Comment;
import com.uis.publications.model.Like;
import com.uis.publications.model.User;
import com.uis.publications.repository.ICommentRepository;
import com.uis.publications.repository.ILikeRepository;
import com.uis.publications.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.mappers.PublicationsMapper;
import com.uis.publications.model.Publication;
import com.uis.publications.repository.IPublicationsRepository;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
/**
 * @author Juan David Morantes Vergara
 **/
@Service
public class PublicationsServiceImp implements IPublicationsService {
    @Autowired
    IPublicationsRepository publicationsRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ILikeRepository likeRepository;
    @Autowired
    ICommentRepository commentRepository;

    @Override
    public List<PublicationsDTO> getNews(Long idUser) {
        List<PublicationsDTO> listDTOS= getPublications();
        ArrayList<PublicationsDTO> listDTO=new ArrayList<>(listDTOS);
        for(PublicationsDTO newList:listDTO){
            if(!Objects.equals(idUser, newList.getId_user())){
                listDTOS.remove(newList);
            }
        }
        return listDTOS;
    }
    @Override
    public List<PublicationsDTO> getPublications(){
        List<Publication> geTrends =  publicationsRepository.findAll();
        List<PublicationsDTO> listDTO= geTrends.stream()
                .map(PublicationsMapper.INSTANCE::toPublicationsDTO).collect(Collectors.toList());
        List<User> users=getUsers();
        for(PublicationsDTO newList: listDTO){
            for(User user:users){
                if(newList.getId_user().equals(user.getId())){
                    newList.setNameUser(user.getNames());
                    newList.setLastNameUser(user.getLastNames());
                    newList.setPhotoUser(user.getUserPhotoUrl());
                }
            }
        }
        listDTO.sort(new ComparePublicationsDTO());
        return listDTO;
    }

    @Override
    public Boolean createPublication(PublicationsDTO publicationsDTO) {
        Publication publication= PublicationsMapper.INSTANCE.toPublication(publicationsDTO);
        this.publicationsRepository.save(publication);
        return true;
    }

    @Override
    public Boolean pullNews(PublicationsDTO publicationsDTO) {
        Publication publication=PublicationsMapper.INSTANCE.toPublication((publicationsDTO));
        this.publicationsRepository.save((publication));
        return true;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean deletePublicationById(Long idPublications) {
        List<Comment>comments=commentRepository.findAll();
        List<Like> likes=likeRepository.findAll();
        List<Publication>publications=publicationsRepository.findAll();
        for (Publication publication:publications){
            if(publication.getId().equals(idPublications)){
                for(Comment comment:comments){
                    if(publication.getId().equals(comment.getId_new())){
                        for(Like like:likes){
                            if(like.getId_comment()!=null) {
                                if (like.getId_comment().equals(comment.getId())) {
                                    this.likeRepository.deleteById(like.getId());
                                }
                            }
                        }
                        this.commentRepository.deleteById(comment.getId());
                    }
                }
                this.publicationsRepository.deleteById(publication.getId());
            }
        }
        return true;
    }

}