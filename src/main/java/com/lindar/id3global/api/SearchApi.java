package com.lindar.id3global.api;

import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.*;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.vo.AuthenticationDetailsResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Arrays;

public class SearchApi extends BaseApi {

    private static final String ENDPOINT = "/Soap11_Stream";
    private static final String AUTHENTICATION_DETAILS_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalSearch/GetAuthenticationDetails";

    private final WebServiceMessageCallback AUTHENTICATION_DETAILS_CALLBACK;


    public SearchApi(AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate, WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback) {
        super(ENDPOINT, accessCredentials, webServiceTemplate);

        this.AUTHENTICATION_DETAILS_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATION_DETAILS_ACTION)));
    }

    public AuthenticationDetailsResponse getAuthenticationDetails(@NonNull String authenticationId) {
        return getAuthenticationDetails(authenticationId, accessCredentials.getOrgId());
    }

    public AuthenticationDetailsResponse getAuthenticationDetails(@NonNull String authenticationId, @NonNull String orgId){
        GetAuthenticationDetails request = buildGetAuthenticationDetails(authenticationId, orgId);
        GetAuthenticationDetailsResponse response = (GetAuthenticationDetailsResponse) marshalSendAndReceive(request, AUTHENTICATION_DETAILS_CALLBACK);
        GlobalAuthenticationDetails value = response.getGetAuthenticationDetailsResult().getValue();

        return AuthenticationDetailsResponse.from(value);
    }

    private GetAuthenticationDetails buildGetAuthenticationDetails(String authenticationId, String orgId){
        GetAuthenticationDetails getAuthenticationDetails = factory.createGetAuthenticationDetails();
        if(StringUtils.isNotBlank(authenticationId)) getAuthenticationDetails.setAuthenticationID(authenticationId);
        if(StringUtils.isNotBlank(orgId)) getAuthenticationDetails.setOrgID(orgId);
        return getAuthenticationDetails;
    }

}
