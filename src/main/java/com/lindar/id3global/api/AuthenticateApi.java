package com.lindar.id3global.api;

import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.*;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.vo.AuthenticateResponse;
import com.lindar.id3global.vo.ProfileVersion;
import com.lindar.id3global.vo.requests.InputData;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AuthenticateResponse> multiAuthenticate(@NonNull String customerReference, @NonNull InputData inputData, @NonNull List<ProfileVersion> profileVersions){
        AuthenticateMP authenticateMP = buildAuthenticateMP(customerReference, inputData, profileVersions);

        AuthenticateMPResponse authenticateMPResponse = (AuthenticateMPResponse) marshalSendAndReceive(authenticateMP, AUTHENTICATE_MP_CALLBACK);

        return authenticateMPResponse.getAuthenticateMPResult().getValue().getGlobalResultData().stream().map(AuthenticateResponse::from).collect(Collectors.toList());
    }

    public AuthenticateResponse singleAuthenticate(@NonNull String customerReference, @NonNull InputData inputData, @NonNull ProfileVersion profileVersion){
        AuthenticateSP authenticateSP = buildAuthenticateSP(customerReference, inputData, profileVersion);

        AuthenticateSPResponse authenticateSPResponse = (AuthenticateSPResponse) marshalSendAndReceive(authenticateSP, AUTHENTICATE_SP_CALLBACK);

        return AuthenticateResponse.from( authenticateSPResponse.getAuthenticateSPResult().getValue() );
    }

    public AuthenticateResponse singleAuthenticate(String customerReference, InputData inputData){
        return singleAuthenticate(customerReference, inputData, new ProfileVersion(accessCredentials.getProfileId(), accessCredentials.getProfileVersion()));
    }

    public AuthenticateResponse incrementalVerification(@NonNull String authenticationId, @NonNull String customerReference, @NonNull InputData inputData, @NonNull ProfileVersion profileVersion){
        IncrementalVerification incrementalVerification = buildIncrementalVerification(authenticationId, customerReference, inputData, profileVersion);

        IncrementalVerificationResponse incrementalVerificationResponse = (IncrementalVerificationResponse) marshalSendAndReceive(incrementalVerification, INCREMENTAL_VERIFICATION_CALLBACK);

        return AuthenticateResponse.from( incrementalVerificationResponse.getIncrementalVerificationResult().getValue() );
    }

    public AuthenticateResponse incrementalVerification(String authenticationId, String customerReference, InputData inputData){
        return incrementalVerification(authenticationId, customerReference, inputData, new ProfileVersion(accessCredentials.getProfileId(), accessCredentials.getProfileVersion()));
    }

    private AuthenticateMP buildAuthenticateMP(String customerReference, InputData inputData, List<ProfileVersion> profileVersions){
        AuthenticateMP authenticateMP = factory.createAuthenticateMP();

        if(profileVersions != null && !profileVersions.isEmpty()) {
            ArrayOfGlobalProfileIDVersion arrayOfGlobalProfileIDVersion = factory.createArrayOfGlobalProfileIDVersion();
            List<GlobalProfileIDVersion> convertedProfiles = profileVersions.stream().map(ProfileVersion::toSoap).collect(Collectors.toList());
            arrayOfGlobalProfileIDVersion.getGlobalProfileIDVersion().addAll(convertedProfiles);
            authenticateMP.setProfileIDVersions(factory.createArrayOfGlobalProfileIDVersion(arrayOfGlobalProfileIDVersion));
        }

        if(StringUtils.isNotBlank(customerReference)) authenticateMP.setCustomerReference(factory.createAuthenticateSPCustomerReference(customerReference));
        if(inputData != null) authenticateMP.setInputData(factory.createAuthenticateSPInputData(inputData.toSoap()));

        return authenticateMP;
    }

    private AuthenticateSP buildAuthenticateSP(String customerReference, InputData inputData, ProfileVersion profileVersion){
        AuthenticateSP authenticateSP = factory.createAuthenticateSP();

        if(profileVersion != null) authenticateSP.setProfileIDVersion(factory.createAuthenticateSPProfileIDVersion(profileVersion.toSoap()));
        if(StringUtils.isNotBlank(customerReference)) authenticateSP.setCustomerReference(factory.createAuthenticateSPCustomerReference(customerReference));
        if(inputData != null) authenticateSP.setInputData(factory.createAuthenticateSPInputData(inputData.toSoap()));

        return authenticateSP;
    }

    private IncrementalVerification buildIncrementalVerification(String authenticationId, String customerReference, InputData inputData, ProfileVersion profileVersion){
        IncrementalVerification incrementalVerification = factory.createIncrementalVerification();

        if(StringUtils.isNotBlank(authenticationId)) incrementalVerification.setAuthenticationID(authenticationId);
        if(profileVersion != null) incrementalVerification.setProfileIDVersion(factory.createIncrementalVerificationProfileIDVersion(profileVersion.toSoap()));
        if(StringUtils.isNotBlank(customerReference)) incrementalVerification.setCustomerReference(factory.createIncrementalVerificationCustomerReference(customerReference));
        if(inputData != null) incrementalVerification.setInputData(factory.createIncrementalVerificationInputData(inputData.toSoap()));

        return incrementalVerification;
    }


}
