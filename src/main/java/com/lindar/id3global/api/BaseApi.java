package com.lindar.id3global.api;

import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.schema.GlobalProfileIDVersion;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;

abstract class BaseApi {
    private final String endpoint;

    protected final AccessCredentials accessCredentials;
    protected final WebServiceTemplate webServiceTemplate;
    protected final GlobalProfileIDVersion defaultProfileVersion;

    public BaseApi(String endpoint, AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate) {
        this.endpoint = endpoint;
        this.accessCredentials = accessCredentials;
        this.webServiceTemplate = webServiceTemplate;
        this.defaultProfileVersion = buildProfileVersion(accessCredentials.getProfileId(), accessCredentials.getProfileVersion());
    }

    protected final Object marshalSendAndReceive(Object payload, WebServiceMessageCallback callback){
        return webServiceTemplate
                .marshalSendAndReceive(accessCredentials.getApiUrl() + endpoint,
                                       payload, callback);
    }

    GlobalProfileIDVersion buildProfileVersion(String id, long version){
        GlobalProfileIDVersion profileVersion = new GlobalProfileIDVersion();
        profileVersion.setID(id);
        profileVersion.setVersion(version);
        return profileVersion;
    }
}
