/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.service.interfaces;

import com.uis.publications.dto.CommentDTO;


import java.util.List;

public interface ICommentService {
    List<CommentDTO> getComments();

    CommentDTO createCommnet(CommentDTO commentDTO);
}
