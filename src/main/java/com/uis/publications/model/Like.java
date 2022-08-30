package com.uis.publications.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author Juan David Morantes Vergara
 **/
@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "LIKES")
public class Like implements Serializable {

    private static final long serialVersionUID = 6373534950499605931L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long id_user;

    @Column(name = "ID_NEW")
    private Long id_new;

    @Column(name = "ID_COMMENT")
    private Long id_comment;

    @Column(name = "IS_LIKE", nullable = false)
    private Boolean is_like;
}
