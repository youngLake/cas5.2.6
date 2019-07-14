package com.young.test;

import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountNotFoundException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * @author Tornado Young
 * @date 2019/7/14 11:39
 */
public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {


    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {

        super(name, servicesManager, principalFactory, order);
        System.err.println("MyAuthenticationHandler has initiated");
    }

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential usernamePasswordCredential, String s) throws GeneralSecurityException, PreventedException {
        System.err.println("MyAuthenticationHandler usernamePasswordCredential.getUsername() : "+usernamePasswordCredential.getUsername());
        if("admin".equals(usernamePasswordCredential.getUsername())){
            return createHandlerResult(usernamePasswordCredential,
                    this.principalFactory.createPrincipal(usernamePasswordCredential.getUsername()),
                    new ArrayList<>(0));
        }else{
            throw new AccountNotFoundException("必须是admin用户才允许通过");
        }
    }
}
