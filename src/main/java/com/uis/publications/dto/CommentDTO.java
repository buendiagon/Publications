/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 8430487342394526507L;
    private Long id;
    private Long id_user;
    private String names;
    private String lastNames;
    private Long id_new;
    private Long id_parent;
    private String description;
    private Date created=new Date();
    private Date updated = new Date();
    private CommentDTO replies=null;
}
