/**
 * @author Juan David Morantes Vergara
 **/

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
@Table(schema = "public", name = "NEWS")
public class Publication implements Serializable{
    private static final long serialVersionUID = 423325368623481945L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_USER", nullable = false)
    private Long id_user;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PHOTO_URL")
    private String photo_url;

    @Column(name = "CREATED", nullable = false)
    private Date created=new Date();

    @Column(name = "UPDATED")
    private Date updated = new Date();



}
