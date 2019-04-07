package com.bbsbackend.components.authenticator;

import com.bbsbackend.components.authenticator.common.Attachable;
import com.bbsbackend.components.authenticator.common.Permission;
import com.bbsbackend.components.authenticator.common.ResetToken;
import com.bbsbackend.components.authenticator.common.UserToken;
import com.bbsbackend.components.authenticator.exceptions.AuthenticatorException;
import com.bbsbackend.components.authenticator.repository.AuthenticatorRepository;
import com.bbsbackend.components.tokenGenerator.TokenGenerator;
import com.bbsbackend.components.validator.Validator;

public interface Authenticator {
    Attachable signUp(String username, String password) throws AuthenticatorException;

    UserToken login(String username, String password) throws AuthenticatorException;

    Permission getLoggedUser(UserToken userToken) throws AuthenticatorException;

    void logout(UserToken userToken);

    ResetToken reqResetPassword(String username) throws AuthenticatorException;

    void resetPassword(ResetToken resetToken, String newPassword) throws AuthenticatorException;

    void remove(String username);

    String getName();

    boolean isAuthenticated(String username, String password);
    
    enum Role {
        USER,
        ADMIN,
    }


    static Authenticator defau1t(String componentName,
                                 AuthenticatorRepository repository,
                                 Validator<String> usernameValidator,
                                 Validator<String> passwordValidator,
                                 TokenGenerator tokenGenerator) {
        return new AuthenticatorImpl(componentName, repository, usernameValidator, passwordValidator, tokenGenerator);
    }

}
