package com.young.test;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tornado Young
 * @date 2019/7/14 11:40
 */
@Configuration("myAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class MyAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casProperties;
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;


    @Bean
    public AuthenticationHandler myAuthenticationHandler() {
        MyAuthenticationHandler handler = new MyAuthenticationHandler(MyAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(), 1);
        return handler;
    }
    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan authenticationEventExecutionPlan) {
        authenticationEventExecutionPlan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}
