package com.lindar.id3global.support;


import lombok.AllArgsConstructor;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import jakarta.xml.soap.*;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@AllArgsConstructor
public class WSSESecurityHeaderRequestWebServiceMessageCallback implements WebServiceMessageCallback {

    private String username;
    private String password;

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {

        try {

            // Assumption: We are using the default SAAJWebMessageFactory

            SaajSoapMessage
                    saajSoapMessage =
                    (SaajSoapMessage)message;

            SOAPMessage
                    soapMessage =
                    saajSoapMessage.getSaajMessage();

            SOAPPart
                    soapPart =
                    soapMessage.getSOAPPart();

            SOAPEnvelope
                    soapEnvelope =
                    soapPart.getEnvelope();

            SOAPHeader
                    soapHeader =
                    soapEnvelope.getHeader();

            Name
                    headerElementName =
                    soapEnvelope.createName(
                            "Security",
                            "wsse",
                            "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
                    );

            // Add "Security" soapHeaderElement to soapHeader
            SOAPHeaderElement
                    soapHeaderElement =
                    soapHeader.addHeaderElement(headerElementName);

            // Add usernameToken to "Security" soapHeaderElement
            SOAPElement
                    usernameTokenSOAPElement =
                    soapHeaderElement.addChildElement("UsernameToken", "wsse");

            // Add username to usernameToken
            SOAPElement
                    userNameSOAPElement =
                    usernameTokenSOAPElement.addChildElement("Username", "wsse");

            userNameSOAPElement.addTextNode(username);

            // Add password to usernameToken
            SOAPElement
                    passwordSOAPElement =
                    usernameTokenSOAPElement.addChildElement("Password", "wsse");

            passwordSOAPElement.addTextNode(password);

        } catch (SOAPException soapException) {
            throw new RuntimeException("WSSESecurityHeaderRequestWebServiceMessageCallback", soapException);
        }
    }
}