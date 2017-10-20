package com.github.serserser.springwebapp.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "t_roles")
@SequenceGenerator(name = "rol_id_seq", sequenceName = "s_rol_id", allocationSize = 1)
public class Role {

    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_id_seq")
    private Long id;

    @Column(name = "rol_code")
    private String code;

    @Column(name = "rol_description")
    private String description;

    @ManyToMany
    @JoinTable(name = "t_role_privileges",
            joinColumns = @JoinColumn(name = "rpv_rol_id",
                    referencedColumnName = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "rpv_prv_id",
                    referencedColumnName = "prv_id"))
    @MapKey(name = "code")
    private Map<String, Privilege> privileges;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Map<String, Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Map<String, Privilege> privileges) {
        this.privileges = privileges;
    }
}
