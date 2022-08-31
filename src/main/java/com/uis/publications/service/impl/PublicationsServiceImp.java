package com.uis.publications.service.impl;

import com.uis.publications.mappers.LikeMapper;
import org.springframework.data.domain.Page;
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

import java.util.*;;
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
    public Page<Publication> getAll(Pageable pageable){
        return publicationsRepository.findAll(pageable);
    }

    @Override
    public List<PublicationsDTO> getNews(Long id_New) {
        List<Publication> geNews =publicationsRepository.findAll();
        List<PublicationsDTO> listDTOS=geNews.stream()
                .map(PublicationsMapper.INSTANCE::toPublicationsDTO).collect(Collectors.toList());
        ArrayList<PublicationsDTO> listDTO=new ArrayList<PublicationsDTO>(listDTOS);
        for(PublicationsDTO newList:listDTO){
            if(id_New==newList.getId()){
                listDTOS.remove(newList);
            }
        }
        Collections.sort(listDTO,new ComparePublicationsDTO());
        return listDTO;
    }

    @Override
    public PublicationsDTO createPublication(PublicationsDTO publicationsDTO) {
        return PublicationsMapper.INSTANCE.toPublicationsDTO(
                this.publicationsRepository.save(PublicationsMapper.INSTANCE.toPublication(publicationsDTO)));
    }

    public List<PublicationsDTO> getTrends(){
        List<Publication> geTrends =  publicationsRepository.findAll();
        List<PublicationsDTO> listDTO= geTrends.stream()
                .map(PublicationsMapper.INSTANCE::toPublicationsDTO).collect(Collectors.toList());
        List<LikeDTO> likeDTOS = likeService.getLikes();
        List<CommentDTO> commentDTOS=commentService.getComments();
        for(PublicationsDTO newList: listDTO){
            List<LikeDTO> numLikes = new ArrayList<>();

            numLikes=addNumLikes(newList,likeDTOS,numLikes);
            newList.setLikes( Long.valueOf(numLikes.size()));
            List<CommentDTO> comments = new ArrayList<>();
            comments=addComments(newList,commentDTOS,comments);
            newList.setComments(comments);
        }
        Collections.sort(listDTO,new ComparePublicationsDTO());
        return listDTO;
    }



    public List<LikeDTO> addNumLikes(PublicationsDTO newList,List<LikeDTO> likeDTOS,List<LikeDTO> numLikes){
        for(LikeDTO likeDTO:likeDTOS){
            if(newList.getId()==likeDTO.getId_new()){
                numLikes.add(likeDTO);
            }
        }
        return numLikes;
    }

    public List<CommentDTO> addComments(PublicationsDTO newList,List<CommentDTO> commentDTOS,List<CommentDTO> comments){
        for(CommentDTO commentDTO:commentDTOS){
            if(commentDTO.getId_parent()==null) {
                for(CommentDTO commentDTO2:commentDTOS){
                    if(commentDTO2.getId_parent()!=null) {
                        if(commentDTO2.getId_parent()==commentDTO.getId()) {
                            commentDTO.setReplies(commentDTO2);
                        }
                    }
                }
                if (newList.getId() == commentDTO.getId_new()) {
                    comments.add(commentDTO);
                }
            }
        }
        return comments;
    }



}
