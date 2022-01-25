package com.lordbyron.auth.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String KEY_STORE_FILE = "filtered/bael-jwt.jks";
    private static final String KEY_STORE_PASSWORD = "bael-pass";
    private static final String KEY_ALIAS = "bael-oauth-jwt";
    private static final String JWK_KID = "bael-key-id";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean KeyPair keyPair()  {
        var ksFile = new ClassPathResource(KEY_STORE_FILE);
        var ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
        return ksFactory.getKeyPair(KEY_ALIAS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
        return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(JWK_KID);
        return new JWKSet(builder.build());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("first-client")
                .secret(passwordEncoder.encode("asdf1234"))
                .authorizedGrantTypes("password")
                .scopes("read", "write")
                .accessTokenValiditySeconds(3600);
    }
}
