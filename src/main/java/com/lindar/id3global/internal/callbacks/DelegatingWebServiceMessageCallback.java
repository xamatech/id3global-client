package com.lindar.id3global.internal.callbacks;

import lombok.AllArgsConstructor;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class DelegatingWebServiceMessageCallback implements WebServiceMessageCallback {
    private List<WebServiceMessageCallback> callbacks;


    @Override
    public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {
        for (WebServiceMessageCallback webServiceMessageCallback : callbacks) {
            webServiceMessageCallback.doWithMessage(webServiceMessage);
        }
    }
}
