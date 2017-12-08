package com.github.serserser.springwebapp.domain;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "t_application_clients")
@SequenceGenerator(name = "s_apc_id", sequenceName = "s_apc_id", allocationSize = 1)
public class ApplicationClient extends Privileged {

    public enum GrantType {
        PASSWORD,
        AUTHORIZATION_CODE
    }

    @Id
    @Column(name = "apc_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_apc_id")
    private Long id;

    @Column(name = "apc_code")
    private String code;

    @Column(name = "apc_name")
    private String name;

    @Column(name = "apc_client_id")
    private String applicationClientId;

    @CollectionTable(name = "t_grant_types", joinColumns = @JoinColumn(name = "gtt_apc_id", referencedColumnName = "apc_id"))
    @ElementCollection(fetch = FetchType.EAGER, targetClass = GrantType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "gtt_name")
    private Set<GrantType> grantTypes;

    @Column(name = "apc_is_secret_required")
    private Boolean secretRequired;

    @Column(name = "apc_client_secret")
    private String hashedPassword;

    @Column(name = "apc_is_scoped")
    private Boolean scoped;

    @MapKey(name = "name")
    @OneToMany(mappedBy = "owner")
    private Map<String, Scope> scopes;

    @CollectionTable(name = "t_redirect_uris", joinColumns = @JoinColumn(name = "rur_apc_id", referencedColumnName = "apc_id"))
    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @Column(name = "rur_uri")
    private Set<String> redirectUris;

    @ManyToMany
    @JoinTable(name = "t_client_roles",
            joinColumns = @JoinColumn(name = "crl_apc_id", referencedColumnName = "apc_id"),
            inverseJoinColumns = @JoinColumn(name = "crl_rol_id", referencedColumnName = "rol_id"))
    private Set<Role> roles;

    @Column(name = "apc_token_validity_seconds")
    private Integer tokenValiditySeconds;

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

    public String getApplicationClientId() {
        return applicationClientId;
    }

    public void setApplicationClientId(String applicationClientId) {
        this.applicationClientId = applicationClientId;
    }

    public Set<GrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<GrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Boolean isSecretRequired() {
        return secretRequired;
    }

    public void setSecretRequired(Boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Boolean isScoped() {
        return scoped;
    }

    public void setScoped(Boolean scoped) {
        this.scoped = scoped;
    }

    public Map<String, Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Map<String, Scope> scopes) {
        this.scopes = scopes;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getTokenValiditySeconds() {
        return tokenValiditySeconds;
    }

    public void setTokenValiditySeconds(Integer tokenValiditySeconds) {
        this.tokenValiditySeconds = tokenValiditySeconds;
    }
}
