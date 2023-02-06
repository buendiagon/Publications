package com.uis.publications.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @autor Juan David Morantes Vergara
 */
@Getter
@Setter
@NoArgsConstructor
public class PublicationsDTO implements Serializable {
    private static final long serialVersionUID = 8297601744673871354L;
    private Long id;
    private Long id_parent;
    @NotNull
    private Long id_career;
    @NotNull
    private Long score;
    @NotNull
    private String description;
    @NotNull
    private Boolean is_question;
}
