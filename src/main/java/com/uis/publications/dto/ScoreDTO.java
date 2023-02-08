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
public class ScoreDTO implements Serializable {
    private static final long serialVersionUID = 6204410800970357596L;
    private Long id;

    private Long id_user;
    @NotNull
    private Long id_input;
    @NotNull
    private Boolean is_positive;
}
