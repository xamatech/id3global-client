package com.lindar.id3global.api;

import com.lindar.id3global.SSLUtilities;
import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.AuthenticateSP;
import com.lindar.id3global.internal.vo.AuthenticateSPResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Arrays;

public class ServiceApi extends WebServiceGatewaySupport {


    private WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback = new WSSESecurityHeaderRequestWebServiceMessageCallback("***REMOVED***", "***REMOVED***");

    public ServiceApi(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.lindar.id3global.vo");

        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    public AuthenticateSPResponse getQuote(AuthenticateSP authenticateSP) {

        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();

        AuthenticateSPResponse response = (AuthenticateSPResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://pilot.id3global.com/ID3gWS/ID3global.svc/Soap11_Auth",
                        authenticateSP, new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback("http://www.id3global.com/ID3gWS/2013/04/IGlobalAuthenticate/AuthenticateSP"))));

        return response;
    }


}
