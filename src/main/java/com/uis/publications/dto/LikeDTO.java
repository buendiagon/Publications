package com.uis.publications.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * @author Juan David Morantes Vergara
 **/
@Getter
@Setter
@NoArgsConstructor
public class LikeDTO implements Serializable {
    private static final long serialVersionUID = -6638394758460860323L;

    private Long id;
    private Long id_user;
    private Long id_new;
    private Long id_comment;
    private Boolean is_like;
}
