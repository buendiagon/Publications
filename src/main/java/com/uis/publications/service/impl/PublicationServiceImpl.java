package com.uis.publications.service.impl;

import com.uis.publications.dto.DetailPublicationDTO;
import com.uis.publications.dto.InputDTO;
import com.uis.publications.exception.DataNotFoundException;
import com.uis.publications.exception.TransactionException;
import com.uis.publications.mappers.DetailPublicationMapper;
import com.uis.publications.mappers.InputMapper;
import com.uis.publications.model.Input;
import com.uis.publications.model.Input_comments;
import com.uis.publications.model.User;
import com.uis.publications.repository.ICommentRepository;
import com.uis.publications.repository.IPublicationRepository;
import com.uis.publications.repository.IUserRepository;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public class PublicationServiceImpl implements IPublicationService {
    private ICommentRepository commentRepository;
    @Autowired
    public void seCommentRepository(ICommentRepository commentRepository) {
        this.commentRepository= commentRepository;
    }

    private IPublicationRepository publicationRepository;
    @Autowired
    public void setPublicationRepository(IPublicationRepository publicationRepository) {
        this.publicationRepository= publicationRepository;
    }


    private IUserRepository userRepository;
    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void SetPublicationRepository(IPublicationRepository publicationRepository) {
        this.publicationRepository=publicationRepository;
    }
    @Override
    public Map<String, Object> getPublications(int page, int size){
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by("score").descending());
            Page<Input> pageTuts;
            pageTuts = publicationRepository.findAllQuestions(paging,true);
            List<Input> inputList;
            inputList = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("publications", getDataUser(inputList));
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return response;
        }catch (Exception e) {
            throw new TransactionException("INTERNAL PROBLEM");
        }
    }
    @Override
    public Map<String, Object> getPublicationsByCareer(int page, int size,Long id){

            Pageable paging = PageRequest.of(page, size, Sort.by("score").descending());
            Page<Input> pageTuts;
            pageTuts = publicationRepository.findAllQuestionsByCareer(paging,id);
            List<Input> inputList;
            inputList = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("publications", inputList);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return response;

    }




    @Override
    public List<InputDTO> getDataUser(List<Input> inputList){
        List<InputDTO> inputDTOS =  new ArrayList<InputDTO>();
        for(Input newList: inputList){
            User user= userRepository.getById(Long.valueOf(newList.getId_user()));
            InputDTO inputDTO= InputMapper.INSTANCE.toInputDTO(newList);
            inputDTO.setUsername(user.getUsername());
            inputDTO.setPhoto_user(user.getUserPhotoUrl());
            inputDTOS.add(inputDTO);
        }
        return inputDTOS;
    }


    @Override
    public DetailPublicationDTO getDetailPublication(Long id_publication) {
        Input input=publicationRepository.findById(id_publication)
                .orElseThrow((() -> new DataNotFoundException("Publication dont exist")));
        DetailPublicationDTO detailPublicationDTO = DetailPublicationMapper.INSTANCE.toDetailPublicationDTO(input);
        detailPublicationDTO.setCommentsList(null);
        List<Input_comments> comment= commentRepository.findByIdInput(id_publication);
        if(!comment.isEmpty()){
            detailPublicationDTO.setCommentsList(comment);
        }
        User user = userRepository.findById(detailPublicationDTO.getId_user())
                .orElseThrow((() -> new DataNotFoundException("User of this publication dont exist")));
        detailPublicationDTO.setUsername(user.getUsername());
        detailPublicationDTO.setPhoto_user(user.getUserPhotoUrl());
        return detailPublicationDTO;
    }
}
