package com.uis.publications.service.impl;

import com.uis.publications.dto.*;
import com.uis.publications.exception.DataNotFoundException;
import com.uis.publications.exception.TransactionException;
import com.uis.publications.exception.ValidationException;
import com.uis.publications.mappers.CommentMapper;
import com.uis.publications.mappers.DetailPublicationMapper;
import com.uis.publications.mappers.InputMapper;
import com.uis.publications.mappers.ScoreMapper;
import com.uis.publications.model.Input;
import com.uis.publications.model.Input_comments;
import com.uis.publications.model.Score;
import com.uis.publications.model.User;
import com.uis.publications.repository.ICommentRepository;
import com.uis.publications.repository.IPublicationRepository;
import com.uis.publications.repository.IScoreRepository;
import com.uis.publications.repository.IUserRepository;
import com.uis.publications.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @autor Juan David Morantes Vergara
 */
@Service
public class PublicationServiceImpl implements IPublicationService {
    @Autowired
    private UserServiceImpl userService;

    private IScoreRepository scoreRepository;
    @Autowired
    public void setScoreRepository(IScoreRepository scoreRepository) {
        this.scoreRepository= scoreRepository;
    }
    private ICommentRepository commentRepository;
    @Autowired
    public void setCommentRepository(ICommentRepository commentRepository) {
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


    @Override
    public Map<String, Object> getPublications(int page, int size){
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Input> pageTuts;
            pageTuts = publicationRepository.findAllQuestions(paging,true);
            return getStringObjectMap(pageTuts);
        }catch (Exception e) {
            throw new TransactionException("INTERNAL PROBLEM");
        }
    }

    @Override
    public Map<String, Object> getPublicationsByCareer(int page, int size,Long id){

            Pageable paging = PageRequest.of(page, size);
            Page<Input> pageTuts;
            pageTuts = publicationRepository.findAllQuestionsByCareer(paging,id);
        return getStringObjectMap(pageTuts);

    }
    private Map<String, Object> getStringObjectMap(Page<Input> pageTuts) {
        List<Input> inputList;
        inputList = pageTuts.getContent();
        List<InputDTO> inputDTOS=new ArrayList<>();

        for(Input input:inputList){
            InputDTO inputDTO= InputMapper.INSTANCE.toInputDTO(input);
            List<Score> score=scoreRepository.getScoreByIdInput(inputDTO.getId());
            int count=0;
            if(!score.isEmpty()){
                for (Score newscore : score) {

                    if (newscore.getIs_positive()) {
                        count = count + 1;
                    } else {
                        count = count - 1;
                    }
                }
            }
            inputDTO.setScore((long) count);
            User user= userRepository.findById(inputDTO.getId_user())
                    .orElseThrow((() -> new DataNotFoundException("User dont exist")));
            inputDTO.setUsername(user.getUsername());
            inputDTO.setPhoto_user(user.getUserPhotoUrl());
            inputDTOS.add(inputDTO);
        }

        inputDTOS.sort((InputDTO o1,InputDTO o2) -> (int) (o2.getScore() - o1.getScore()));
        Map<String, Object> response = new HashMap<>();
        response.put("publications", inputDTOS);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
        return response;
    }
    @Override
    public List<InputDTO> getDataUser(List<Input> inputList){
        List<InputDTO> inputDTOS =  new ArrayList<>();
        for(Input newList: inputList){
            User user= userRepository.findById(newList.getId_user())
                    .orElseThrow((() -> new DataNotFoundException("User dont exist")));
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
        if(!input.getIs_question()){
            throw new ValidationException("Is not a question");
        }
        DetailPublicationDTO detailPublicationDTO = DetailPublicationMapper.INSTANCE.toDetailPublicationDTO(input);
        detailPublicationDTO.setCommentsList(null);
        AsignateScore(id_publication, detailPublicationDTO);
        detailPublicationDTO.setResponseslist(ResponsesInputs(id_publication));
        return detailPublicationDTO;
    }
    @Override
    public List<DetailPublicationDTO> ResponsesInputs(Long id_publication){
        List<Input> responses = publicationRepository.findAllResponses(id_publication);
        List<DetailPublicationDTO> list= new ArrayList<>();
        for(Input newlist:responses){
            DetailPublicationDTO detailPublicationDTO = DetailPublicationMapper.INSTANCE.toDetailPublicationDTO(newlist);
            AsignateScore(newlist.getId(), detailPublicationDTO);
            list.add(detailPublicationDTO);
        }
        return list;
    }

    @Override
    public Boolean createPublication(PublicationDTO publicationDTO,String token) {
        UserDTO userDTO=userService.getUserDataByToken(token);
        publicationDTO.setId_user(userDTO.getId());
        Input input = InputMapper.INSTANCE.toInput(publicationDTO);
        this.publicationRepository.save(input);
        return true;
    }
//todo El get comment asigna mal el username y la photo

    @Override
    public Boolean createComment(CommentDTO comment,String token) {
        UserDTO userDTO=userService.getUserDataByToken(token);
        comment.setId_user(userDTO.getId());
        Input_comments comments= CommentMapper.INSTANCE.toComment(comment);
        this.commentRepository.save(comments);
        return true;
    }

    @Override
    public Boolean createRate(ScoreDTO scoreDTO, String token) {
        UserDTO userDTO=userService.getUserDataByToken(token);

        List<Score> score1=scoreRepository.getScoreByIdUser(userDTO.getId());
        int count=0;
        if(!score1.isEmpty()){
            for (Score newscore : score1) {

                if (newscore.getIs_positive()) {
                    count = count + 1;
                } else {
                    count = count - 1;
                }
            }
            if(count<2){
                throw new ValidationException("User is not trust");
            }
        }



        if (scoreRepository.getScoreByIdUserAndInput(userDTO.getId(), scoreDTO.getId_input())==null) {

            scoreDTO.setId_user(userDTO.getId());
            Score score= ScoreMapper.INSTANCE.toScore(scoreDTO);

            scoreRepository.save(score);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Boolean deleteRate(Long id_input, String token) {
        UserDTO userDTO=userService.getUserDataByToken(token);
        Score score=scoreRepository.getScoreByIdUserAndInput(userDTO.getId(),id_input);
        try{

            scoreRepository.deleteById(score.getId());
        }catch(Exception e){
            throw new TransactionException("Score dont exits");
        }
        return true;
    }

    @Override
    public List<InputDTO> searchPublication(String description) {
        description = description.replace(" "," & ");
        List<Input> inputList=publicationRepository.findAllByDescription(description);
        return InputMapper.INSTANCE.toInputDTOList(inputList);
    }


    private void AsignateScore(Long id_publication, DetailPublicationDTO detailPublicationDTO) {
        set_comments_user(id_publication, detailPublicationDTO);

        List<Score> score=scoreRepository.getScoreByIdInput(id_publication);
        int count=0;
        if(!score.isEmpty()){
            for (Score newscore : score) {

                if (newscore.getIs_positive()) {
                    count = count + 1;
                } else {
                    count = count - 1;
                }
            }
        }
        detailPublicationDTO.setScore((long) count);
    }

    private void set_comments_user(Long id_publication, DetailPublicationDTO detailPublicationDTO) {
        List<Input_comments>  c=commentRepository.findByIdInput(id_publication);
        List<CommentDTO> comment = c.stream()
                .map(CommentMapper.INSTANCE::toCommentDTO).collect(Collectors.toList());;
        if (!comment.isEmpty()) {
            for(CommentDTO comments:comment){
                User user = userRepository.findById(comments.getId_user())
                        .orElseThrow((() -> new DataNotFoundException("User of this publication dont exist")));
                comments.setUsername(user.getUsername());
                comments.setPhoto_user(user.getUserPhotoUrl());
            }
            detailPublicationDTO.setCommentsList(comment);
        }

        User user = userRepository.findById(detailPublicationDTO.getId_user())
                .orElseThrow((() -> new DataNotFoundException("User of this publication dont exist")));
        detailPublicationDTO.setUsername(user.getUsername());
        detailPublicationDTO.setPhoto_user(user.getUserPhotoUrl());
    }
}
