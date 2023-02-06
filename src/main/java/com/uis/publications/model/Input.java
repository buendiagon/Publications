package com.uis.publications.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @autor Juan David Morantes Vergara
 */
@Entity
@Data
@NoArgsConstructor
@Table(schema = "campuslink", name = "input")
public class Input implements Serializable {
    private static final long serialVersionUID = -5315725790726268470L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_parent")
    private String id_parent;
    @Column(name = "id_career", nullable = false)
    private String id_career;
    @Column(name = "score", nullable = false)
    private Long score;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "is_question", nullable = false)
    private Boolean is_question;
    @Column(name = "id_user", nullable = false)
    private String id_user;
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    @Column(name = "updated")
    @Temporal(TemporalType.DATE)
    private Date updated = new Date();
}
