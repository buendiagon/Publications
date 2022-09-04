/**
 * @author Juan David Morantes Vergara
 **/

package com.uis.publications.service.interfaces;

import com.uis.publications.dto.CommentDTO;


import java.util.List;

public interface ICommentService {
    List<CommentDTO> getCommentsByIdPublication(Long idPublication);

    CommentDTO createCommnet(CommentDTO commentDTO);
}
