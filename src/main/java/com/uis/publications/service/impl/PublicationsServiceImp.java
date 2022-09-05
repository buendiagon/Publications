package com.uis.publications.service.impl;

import com.uis.publications.model.User;
import com.uis.publications.repository.IUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.mappers.PublicationsMapper;
import com.uis.publications.model.Publication;
import com.uis.publications.repository.IPublicationsRepository;
import com.uis.publications.service.interfaces.ICommentService;
import com.uis.publications.service.interfaces.ILikeService;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ILikeService likeService;
    @Autowired
    ICommentService commentService;

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
    @SneakyThrows
    @Override
    public List<PublicationsDTO> getPublications(){
        List<Publication> geTrends =  publicationsRepository.findAll();
        List<PublicationsDTO> listDTO= geTrends.stream()
                .map(PublicationsMapper.INSTANCE::toPublicationsDTO).collect(Collectors.toList());
        List<User> users=getUsers();
        for(PublicationsDTO newList: listDTO){
            changeDate(newList);


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

    public void changeDate(PublicationsDTO publicationsDTO) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date updated= publicationsDTO.getUpdated();
        Date created= publicationsDTO.getCreated();
        String updatedGood= formatter.format(updated);
        String createdGood=formatter.format(created);

        publicationsDTO.setCreated(formatter.parse(createdGood));
        publicationsDTO.setUpdated(formatter.parse(updatedGood));

    }

}