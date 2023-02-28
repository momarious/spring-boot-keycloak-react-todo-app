package com.momarious.todoapi.service;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {


    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) authentication
                .getPrincipal();
        return keycloakPrincipal.getKeycloakSecurityContext().getToken().getSubject();
    }

}
