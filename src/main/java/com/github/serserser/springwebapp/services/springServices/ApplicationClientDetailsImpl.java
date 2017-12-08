package com.github.serserser.springwebapp.services.springServices;

import com.github.serserser.springwebapp.domain.ApplicationClient;
import com.github.serserser.springwebapp.domain.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationClientDetailsImpl extends Authorized implements ClientDetails {

    private ApplicationClient client;
    private Set<GrantedAuthority> privileges;

    public ApplicationClientDetailsImpl(ApplicationClient client) {
        this.client = client;
        this.privileges = mapToGrantedAuthority(client.getPrivileges());
    }

    @Override
    public String getClientId() {
        return client.getApplicationClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        // TODO: [jgolda] resource server ids not handled yet
        return new HashSet<>();
    }

    @Override
    public boolean isSecretRequired() {
        return client.isSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return client.getHashedPassword();
    }

    @Override
    public boolean isScoped() {
        return client.isScoped();
    }

    @Override
    public Set<String> getScope() {
        return client.getScopes().keySet();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getGrantTypes().stream()
                .map(grantType -> grantType.name().toLowerCase())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return client.getRedirectUris();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return privileges;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return Optional.ofNullable(client.getScopes().get(scope))
                .map(Scope::isAutoApprove)
                .orElse(true); // TODO: [jgolda] sprawdzić, do czego służy auto-approve, pewnie do authorization_code
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
