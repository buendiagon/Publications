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
@Table(schema = "campuslink", name = "input_comments")
public class Input_comments implements Serializable {
    private static final long serialVersionUID = 2259848166762293081L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_user", nullable = false)
    private Long id_user;
    @Column(name = "id_input", nullable = false)
    private Long id_input;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created = new Date();

    @Column(name = "updated")
    @Temporal(TemporalType.DATE)
    private Date updated = new Date();

}
