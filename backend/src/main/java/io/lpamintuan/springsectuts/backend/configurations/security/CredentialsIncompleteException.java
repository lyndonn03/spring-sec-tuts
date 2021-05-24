package io.lpamintuan.springsectuts.backend.configurations.security;

import org.springframework.security.core.AuthenticationException;

public class CredentialsIncompleteException extends AuthenticationException {


    public CredentialsIncompleteException(String msg) {
        super(msg);
    }


}
