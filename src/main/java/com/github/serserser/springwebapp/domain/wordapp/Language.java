package com.github.serserser.springwebapp.domain.wordapp;

import javax.persistence.*;

@Entity
@Table(name = "T_LANGUAGES")
@SequenceGenerator(name = "S_LNG_ID", sequenceName = "S_LNG_ID")
public class Language {

    @Id
    @Column(name = "LNG_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_LNG_ID")
    private Long id;

    @Column(name = "LNG_CODE")
    private String code;

    @Column(name = "LNG_NAME")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
