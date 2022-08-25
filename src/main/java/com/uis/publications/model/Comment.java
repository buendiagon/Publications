package com.uis.publications.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Juan David Morantes Vergara
 **/
@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "COMMENTS")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1149971969234207346L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long id_user;

    @Column(name = "ID_NEW", nullable = false)
    private Long id_new;

    @Column(name = "ID_PARENT", nullable = false)
    private Long id_parent;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CREATED", nullable = false)
    private Date created=new Date();

    @Column(name = "UPDATED")
    private Date updated = new Date();
}
