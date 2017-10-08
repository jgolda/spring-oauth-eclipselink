package com.github.serserser.springwebapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_privileges")
@SequenceGenerator(name = "prv_id_seq", sequenceName = "s_prv_id")
public class Privilege {

    @Id
    @Column(name = "prv_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prv_id_seq")
    private Long id;

    @Column(name = "prv_code")
    private String code;

    @Column(name = "prv_description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
