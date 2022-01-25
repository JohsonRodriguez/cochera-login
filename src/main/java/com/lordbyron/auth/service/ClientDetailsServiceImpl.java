package com.lordbyron.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }
}
