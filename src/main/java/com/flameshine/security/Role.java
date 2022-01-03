package com.flameshine.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration that represents user roles.
 */

public enum Role implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return code;
    }
}