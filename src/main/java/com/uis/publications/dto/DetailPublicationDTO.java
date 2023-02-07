package com.uis.publications.dto;

import com.uis.publications.model.Input_comments;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @autor Juan David Morantes Vergara
 */
@Getter
@Setter
public class DetailPublicationDTO implements Serializable {
    private static final long serialVersionUID = -6251352999541805251L;
    private Long id;
    @NotNull
    private Long id_user;
    private String username;
    private String photo_user;
    private Long id_parent;
    @NotNull
    private Long id_career;
    @NotNull
    private Long score;
    @NotNull
    private String description;
    @NotNull
    private Boolean is_question;
    private String title;
    private List<Input_comments> commentsList;
    private List<DetailPublicationDTO> responseslist;
}
