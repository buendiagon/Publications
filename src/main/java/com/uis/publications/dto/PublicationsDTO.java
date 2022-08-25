package com.uis.publications.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Juan David Morantes Vergara
 **/
@Getter
@Setter
@NoArgsConstructor
public class PublicationsDTO implements Serializable {

    private static final long serialVersionUID = 8846700335219967805L;
    private Long id;

    private Long id_user;

    private String title;

    private String description;

    private String photo_url;
    @NotNull
    private Date created=new Date();

    private Date updated = new Date();

    private Long likes;
    private List<CommentDTO> comments;

}
