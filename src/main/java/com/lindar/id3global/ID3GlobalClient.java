package com.lindar.id3global;

import com.lindar.id3global.api.AuthenticateApi;
import com.lindar.id3global.api.CredentialsApi;
import com.lindar.id3global.api.SearchApi;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.vo.AccessCredentials;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

public class ID3GlobalClient {

    private static final String CONTEXT_PATH = "com.lindar.id3global.internal.vo";

    private final AuthenticateApi authenticateApi;
    private final SearchApi searchApi;
    private final CredentialsApi credentialsApi;


    private ID3GlobalClient(AccessCredentials accessCredentials){
        WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback = new WSSESecurityHeaderRequestWebServiceMessageCallback(accessCredentials.getUsername(), accessCredentials.getPassword());
        WebServiceTemplate webServiceTemplate = buildWebServiceTemplate();

        this.authenticateApi = new AuthenticateApi(accessCredentials, webServiceTemplate, authenticationCallback);
        this.searchApi = new SearchApi(accessCredentials, webServiceTemplate, authenticationCallback);
        this.credentialsApi = new CredentialsApi(accessCredentials, webServiceTemplate);
    }

    public AuthenticateApi authenticate() {
        return authenticateApi;
    }

    public SearchApi search() {
        return searchApi;
    }

    public CredentialsApi credentials() {
        return credentialsApi;
    }

    public static ID3GlobalClient build(String apiUrl, String username, String password, String defaultProfileId, int defaultProfileVersion, String defaultOrgId) {
        AccessCredentials accessCredentials = new AccessCredentials();
        accessCredentials.setApiUrl(apiUrl);
        accessCredentials.setProfileId(defaultProfileId);
        accessCredentials.setProfileVersion(defaultProfileVersion);
        accessCredentials.setUsername(username);
        accessCredentials.setPassword(password);
        accessCredentials.setOrgId(defaultOrgId);
        return new ID3GlobalClient(accessCredentials);
    }

    public static ID3GlobalClient build(AccessCredentials accessCredentials){
        return new ID3GlobalClient(accessCredentials);
    }

    private WebServiceTemplate buildWebServiceTemplate(){
        Jaxb2Marshaller jaxb2Marshaller = buildMarshaller();
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);

        return webServiceTemplate;
    }

    private Jaxb2Marshaller buildMarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);

        return marshaller;
    }
}
