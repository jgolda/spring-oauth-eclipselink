package com.github.serserser.springwebapp.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration
        extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AccessTokenConverter accessTokenConverter;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("sampleClientId")
                .authorizedGrantTypes("implicit")
                .scopes("read")
                .autoApprove(true)
            .and()
                .withClient("clientIdPassword")
                .secret("secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("read");
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager);
    }
}