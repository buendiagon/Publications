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
public class PublicationDTO implements Serializable {
    private static final long serialVersionUID = 6157977660515457077L;
    @NotNull
    private Long id_user;
    @NotNull
    private Long id_career;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Boolean is_question;
    private Long id_parent;
}
