package com.lindar.id3global.api;

import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.AuthenticateSP;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.vo.AuthenticateSPRequest;
import com.lindar.id3global.vo.AuthenticateSPResponse;
import com.lindar.id3global.vo.ProfileVersion;
import lombok.AllArgsConstructor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Arrays;

@AllArgsConstructor
public class AuthenticateApi {
    private static final ObjectFactory factory = ObjectFactory.getInstance();

    private static final String AUTHENTICATE_SP_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalAuthenticate/AuthenticateSP";

    private final AccessCredentials accessCredentials;
    private final WebServiceTemplate webServiceTemplate;
    private final WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback;


    public AuthenticateSPResponse singleAuthenticate(AuthenticateSPRequest request){

        AuthenticateSP authenticateSP = request.toSoap();
        ensureProfileVersion(authenticateSP);

        AuthenticateSPResponse response = AuthenticateSPResponse.from( (com.lindar.id3global.internal.vo.AuthenticateSPResponse)webServiceTemplate
                .marshalSendAndReceive(accessCredentials.getApiUrl(),
                        authenticateSP, new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATE_SP_ACTION)))));

        return response;
    }


    private void ensureProfileVersion(AuthenticateSP authenticateSP){
        if(authenticateSP.getProfileIDVersion() == null){
            authenticateSP.setProfileIDVersion(factory.createAuthenticateSPProfileIDVersion(new ProfileVersion(accessCredentials.getProfileId(), accessCredentials.getProfileVersion()).toSoap()));
        }
    }

}
