package com.github.serserser.springwebapp.domain;

import com.github.serserser.springwebapp.domain.validation.EqualPasswords;
import com.github.serserser.springwebapp.domain.validation.ValidRequiredFields;
import com.github.serserser.springwebapp.domain.validation.ValidatePassword;
import com.github.serserser.springwebapp.domain.validation.email.Registration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "t_users")
@SequenceGenerator(name = "usr_id_seq", sequenceName = "s_usr_id", allocationSize = 1)
@ValidRequiredFields
@EqualPasswords
public class User implements Serializable {

    public User() {
    }

    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_id_seq")
    private Long id;

    @Size(max = 100)
    @Column(name = "usr_login")
    private String login;

    @Size(max = 256)
    @Column(name = "usr_email")
    @NotEmpty(groups = Registration.class)
    private String email;

    @Column(name = "usr_password")
    private String password;

    @Transient
    @ValidatePassword(groups = Registration.class)
    private String plainPassword;

    @Transient
    private String retypedPlainPassword;

    @Size(max = 200)
    @NotEmpty
    @Column(name = "usr_first_name")
    private String firstName;

    @Size(max = 200)
    @NotEmpty
    @Column(name = "usr_last_name")
    private String lastName;

    @JoinTable(name = "t_user_roles",
            joinColumns = @JoinColumn(name = "url_usr_id",
                    referencedColumnName = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "url_rol_id",
                    referencedColumnName = "rol_id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getRetypedPlainPassword() {
        return retypedPlainPassword;
    }

    public void setRetypedPlainPassword(String retypedPlainPassword) {
        this.retypedPlainPassword = retypedPlainPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Map<String, Privilege> getPrivileges() {
        return roles.stream()
                .map(Role::getPrivileges)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, last) -> last));
    }
}
