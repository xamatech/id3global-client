package com.lindar.id3global.api;

import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.*;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.vo.Account;
import lombok.NonNull;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Collections;

public class CredentialsApi extends BaseApi {

    private static final String ENDPOINT = "/Soap11_NoAuth";
    private static final String CHECK_CREDENTIALS_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalCredentials/CheckCredentials";

    private final WebServiceMessageCallback CHECK_CREDENTIALS_CALLBACK;


    public CredentialsApi(AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate) {
        super(ENDPOINT, accessCredentials, webServiceTemplate);

        this.CHECK_CREDENTIALS_CALLBACK =  new DelegatingWebServiceMessageCallback(Collections.singletonList(new SoapActionCallback(CHECK_CREDENTIALS_ACTION)));
    }

    public Account checkCredentials() {
        return checkCredentials(accessCredentials.getUsername(), accessCredentials.getPassword());
    }

    public Account checkCredentials(@NonNull String username, @NonNull String password){
        CheckCredentials request = buildCheckCredentials(username, password);
        CheckCredentialsResponse response = (CheckCredentialsResponse) marshalSendAndReceive(request, CHECK_CREDENTIALS_CALLBACK);

        GlobalAccount value = response.getCheckCredentialsResult().getValue();

        return Account.from(value);
    }

    private CheckCredentials buildCheckCredentials(String username, String password){
        CheckCredentials request = factory.createCheckCredentials();
        request.setAccountName(factory.createCheckCredentialsAccountName(username));
        request.setPassword(factory.createCheckCredentialsPassword(password));
        return request;
    }

}
