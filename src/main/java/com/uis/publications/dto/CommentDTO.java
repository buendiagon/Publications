package com.uis.publications.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @autor Juan David Morantes Vergara
 */
@Getter
@Setter
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -7847140030979423508L;
    private Long id;
    @NotNull
    private Long id_user;
    @NotNull
    private Long id_input;
    @NotNull
    private String description;



}
