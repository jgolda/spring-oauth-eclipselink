package com.github.serserser.springwebapp.domain.wordapp;

import javax.persistence.*;

@Entity
@Table(name = "T_WORDS")
@SequenceGenerator(name = "SEQ_WRD_ID", sequenceName = "SEQ_WRD_ID")
public class Word {

    @Id
    @Column(name = "WRD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WRD_ID")
    private Long id;

    @Column(name = "WRD_WORD")
    private String word;

    @Column(name = "WRD_NOTE")
    private String note;

    @OneToOne(targetEntity = Language.class)
    @JoinColumn(name = "WRD_LANGUAGE")
    private Language language;
}
