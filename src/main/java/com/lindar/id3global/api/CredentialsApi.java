package com.lindar.id3global.api;

import com.lindar.id3global.schema.GlobalAccount;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.schema.CheckCredentials;
import com.lindar.id3global.schema.CheckCredentialsResponse;
import com.lindar.id3global.support.DelegatingWebServiceMessageCallback;
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

    public GlobalAccount checkCredentials() {
        return checkCredentials(accessCredentials.getUsername(), accessCredentials.getPassword());
    }

    public GlobalAccount checkCredentials(@NonNull String username, @NonNull String password){
        CheckCredentials request = new CheckCredentials();
        request.setAccountName(username);
        request.setPassword(password);

        return ((CheckCredentialsResponse) marshalSendAndReceive(request, CHECK_CREDENTIALS_CALLBACK)).getCheckCredentialsResult();
    }

}
