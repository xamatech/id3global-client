package com.lindar.id3global.api;


import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.schema.*;
import com.lindar.id3global.support.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.support.WSSESecurityHeaderRequestWebServiceMessageCallback;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Arrays;
import java.util.List;

public class AuthenticateApi extends BaseApi {

    private static final String ENDPOINT = "/Soap11_Auth";

    private static final String AUTHENTICATE_SP_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalAuthenticate/AuthenticateSP";
    private static final String AUTHENTICATE_MP_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalAuthenticate/AuthenticateMP";
    private static final String INCREMENTAL_VERIFICATION_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalAuthenticate/IncrementalVerification";

    private final WebServiceMessageCallback AUTHENTICATE_SP_CALLBACK;
    private final WebServiceMessageCallback AUTHENTICATE_MP_CALLBACK;
    private final WebServiceMessageCallback INCREMENTAL_VERIFICATION_CALLBACK;

    public AuthenticateApi(AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate, WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback) {
        super(ENDPOINT, accessCredentials, webServiceTemplate);

        this.AUTHENTICATE_SP_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATE_SP_ACTION)));
        this.AUTHENTICATE_MP_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATE_MP_ACTION)));
        this.INCREMENTAL_VERIFICATION_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(INCREMENTAL_VERIFICATION_ACTION)));
    }

    public List<GlobalResultData> multiAuthenticate(@NonNull String customerReference, @NonNull GlobalInputData inputData, @NonNull List<GlobalProfileIDVersion> profileVersions){
        AuthenticateMP authenticateMP = new AuthenticateMP();

        if(profileVersions != null && !profileVersions.isEmpty()) {
            authenticateMP.setProfileIDVersions(profileVersions);
        }

        if(StringUtils.isNotBlank(customerReference)) authenticateMP.setCustomerReference(customerReference);
        if(inputData != null) authenticateMP.setInputData(inputData);

        return ((AuthenticateMPResponse) marshalSendAndReceive(authenticateMP, AUTHENTICATE_MP_CALLBACK)).getAuthenticateMPResult();
    }

    public GlobalResultData singleAuthenticate(@NonNull String customerReference, @NonNull GlobalInputData inputData, @NonNull GlobalProfileIDVersion profileVersion){
        AuthenticateSP authenticateSP = new AuthenticateSP();

        if(profileVersion != null) authenticateSP.setProfileIDVersion(profileVersion);
        if(StringUtils.isNotBlank(customerReference)) authenticateSP.setCustomerReference(customerReference);
        if(inputData != null) authenticateSP.setInputData(inputData);

        return ((AuthenticateSPResponse) marshalSendAndReceive(authenticateSP, AUTHENTICATE_SP_CALLBACK)).getAuthenticateSPResult();
    }

    public GlobalResultData singleAuthenticate(String customerReference, GlobalInputData inputData){
        return singleAuthenticate(customerReference, inputData, defaultProfileVersion);
    }

    public GlobalResultData incrementalVerification(@NonNull String authenticationId, @NonNull String customerReference, @NonNull GlobalInputData inputData, @NonNull GlobalProfileIDVersion profileVersion){
        IncrementalVerification incrementalVerification = new IncrementalVerification();

        if(StringUtils.isNotBlank(authenticationId)) incrementalVerification.setAuthenticationID(authenticationId);
        if(profileVersion != null) incrementalVerification.setProfileIDVersion(profileVersion);
        if(StringUtils.isNotBlank(customerReference)) incrementalVerification.setCustomerReference(customerReference);
        if(inputData != null) incrementalVerification.setInputData(inputData);

        return ((IncrementalVerificationResponse) marshalSendAndReceive(incrementalVerification, INCREMENTAL_VERIFICATION_CALLBACK)).getIncrementalVerificationResult();
    }

    public GlobalResultData incrementalVerification(String authenticationId, String customerReference, GlobalInputData inputData){
        return incrementalVerification(authenticationId, customerReference, inputData, defaultProfileVersion);
    }
}