package com.lindar.id3global.api;

import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.AccessCredentials;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;

abstract class BaseApi {
    protected static final ObjectFactory factory = ObjectFactory.getInstance();
    private final String endpoint;

    protected final AccessCredentials accessCredentials;
    private final WebServiceTemplate webServiceTemplate;

    public BaseApi(String endpoint, AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate) {
        this.endpoint = endpoint;
        this.accessCredentials = accessCredentials;
        this.webServiceTemplate = webServiceTemplate;
    }

    protected final Object marshalSendAndReceive(Object payload, WebServiceMessageCallback callback){
        return webServiceTemplate
                .marshalSendAndReceive(accessCredentials.getApiUrl() + endpoint,
                                       payload, callback);
    }
}
