package com.zenika.resanet.service.factory;

import com.zenika.resanet.dao.OperatorDao;
import com.zenika.resanet.dao.impl.OperatorDaoImpl;
import com.zenika.resanet.service.AuthenticationService;

import com.zenika.resanet.service.impl.AuthenticationServiceImpl;



public class ServiceFactory {
    private AuthenticationService authenticationService;


    private static ServiceFactory instance;

    static {
        instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
        authenticationService = new AuthenticationServiceImpl(new OperatorDaoImpl());

    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
