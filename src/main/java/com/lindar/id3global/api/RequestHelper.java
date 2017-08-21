package com.lindar.id3global.api;

import com.lindar.id3global.internal.vo.AuthenticateSP;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.AuthenticateSPRequest;

class RequestHelper {

    private static ObjectFactory objectFactory = new ObjectFactory();

    public static AuthenticateSP toSoapRequest(AuthenticateSPRequest request){

        AuthenticateSP authenticateSP = objectFactory.createAuthenticateSP();


        return authenticateSP;
    }
}
