package com.lindar.id3global;

import com.lindar.id3global.api.AuthenticateApi;
import com.lindar.id3global.api.CredentialsApi;
import com.lindar.id3global.api.SearchApi;
import com.lindar.id3global.support.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.vo.AccessCredentials;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.WebServiceMessageSender;

public class ID3GlobalClient {

    private static final String CONTEXT_PATH = "com.lindar.id3global.schema";

    private final AuthenticateApi authenticateApi;
    private final SearchApi searchApi;
    private final CredentialsApi credentialsApi;

    private ID3GlobalClient(AccessCredentials accessCredentials, WebServiceMessageSender webServiceMessageSender){
        WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback = new WSSESecurityHeaderRequestWebServiceMessageCallback(accessCredentials.getUsername(), accessCredentials.getPassword());
        WebServiceTemplate webServiceTemplate = buildWebServiceTemplate(webServiceMessageSender);

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
        return new ID3GlobalClient(accessCredentials, null);
    }

    public static ID3GlobalClient build(AccessCredentials accessCredentials){
        return new ID3GlobalClient(accessCredentials, null);
    }

    public static ID3GlobalClient build(AccessCredentials accessCredentials, WebServiceMessageSender webServiceMessageSender){
        return new ID3GlobalClient(accessCredentials, webServiceMessageSender);
    }

    private WebServiceTemplate buildWebServiceTemplate(WebServiceMessageSender webServiceMessageSender){
        Jaxb2Marshaller jaxb2Marshaller = buildMarshaller();
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        if (webServiceMessageSender != null) {
            webServiceTemplate.setMessageSender(webServiceMessageSender);
        }

        return webServiceTemplate;
    }

    private Jaxb2Marshaller buildMarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);

        return marshaller;
    }
}
