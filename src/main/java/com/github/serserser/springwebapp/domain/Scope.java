package com.github.serserser.springwebapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_scopes")
@SequenceGenerator(sequenceName = "s_scp_id", name = "s_scp_id", allocationSize = 1)
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_scp_id")
    @Column(name = "scp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scp_apc_id", referencedColumnName = "apc_id")
    private ApplicationClient owner;

    @Column(name = "scp_name")
    private String name;

    @Column(name = "scp_is_auto_approve")
    private Boolean autoApprove;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationClient getOwner() {
        return owner;
    }

    public void setOwner(ApplicationClient owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(Boolean autoApprove) {
        this.autoApprove = autoApprove;
    }
}
