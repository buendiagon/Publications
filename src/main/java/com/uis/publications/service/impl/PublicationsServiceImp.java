package com.uis.publications.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.uis.publications.dto.CommentDTO;
import com.uis.publications.dto.LikeDTO;
import com.uis.publications.dto.PublicationsDTO;
import com.uis.publications.mappers.PublicationsMapper;
import com.uis.publications.model.Publication;
import com.uis.publications.repository.IPublicationsRepository;
import com.uis.publications.service.interfaces.ICommentService;
import com.uis.publications.service.interfaces.ILikeService;
import com.uis.publications.service.interfaces.IPublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ILikeService likeService;

    @Autowired
    ICommentService commentService;


    @Override
    public Page<PublicationsDTO> getTrends(Pageable pageable) {
        List<PublicationsDTO> listDTO= getNew();
        return createPage(pageable,listDTO);
    }

    @Override
    public Page<PublicationsDTO> getNews(Long id_New,Pageable pageable) {
        List<PublicationsDTO> listDTOS= getNew();
        ArrayList<PublicationsDTO> listDTO=new ArrayList<>(listDTOS);
        for(PublicationsDTO newList:listDTO){
            if(Objects.equals(id_New, newList.getId())){
                listDTOS.remove(newList);
            }
        }
        return createPage(pageable,listDTOS);
    }

    private List<PublicationsDTO> getNew(){
        List<Publication> geTrends =  publicationsRepository.findAll();
        List<PublicationsDTO> listDTO= geTrends.stream()
                .map(PublicationsMapper.INSTANCE::toPublicationsDTO).collect(Collectors.toList());
        List<LikeDTO> likeDTOS = likeService.getLikes();
        List<CommentDTO> commentDTOS=commentService.getComments();
        for(PublicationsDTO newList: listDTO){
            List<LikeDTO> numLikes = new ArrayList<>();

            addNumLikes(newList, likeDTOS, numLikes);
            newList.setLikes((long) numLikes.size());
            List<CommentDTO> comments = new ArrayList<>();
            addComments(newList, commentDTOS, comments);
            if(!comments.isEmpty()){
                newList.setComments(comments);
            }
        }
        listDTO.sort(new ComparePublicationsDTO());

        return listDTO;
    }

    private Page<PublicationsDTO> createPage(Pageable pageable,List<PublicationsDTO> listDTO) {
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listDTO.size());

        return new PageImpl<>(listDTO.subList(start, end), pageable, listDTO.size());
    }


    private void addNumLikes(PublicationsDTO newList, List<LikeDTO> likeDTOS, List<LikeDTO> numLikes){
        for(LikeDTO likeDTO:likeDTOS){
            if(Objects.equals(newList.getId(), likeDTO.getId_new())){
                numLikes.add(likeDTO);
            }
        }
    }

    private void addComments(PublicationsDTO newList, List<CommentDTO> commentDTOS, List<CommentDTO> comments){
        for(CommentDTO commentDTO:commentDTOS){
            if(commentDTO.getId_parent()==null) {
                for(CommentDTO commentDTO2:commentDTOS){
                    if(commentDTO2.getId_parent()!=null) {
                        if(Objects.equals(commentDTO2.getId_parent(), commentDTO.getId())) {
                            commentDTO.setReplies(commentDTO2);
                        }
                    }
                }
                if (Objects.equals(newList.getId(), commentDTO.getId_new())) {
                    comments.add(commentDTO);
                }
            }
        }
    }

    @Override
    public PublicationsDTO createPublication(PublicationsDTO publicationsDTO) {
        return PublicationsMapper.INSTANCE.toPublicationsDTO(
                this.publicationsRepository.save(PublicationsMapper.INSTANCE.toPublication(publicationsDTO)));
    }

}
