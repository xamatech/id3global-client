package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalIdentityDocuments;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.identity_documents.uk.UKIndentityDocuments;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdentityDocuments implements SoapInterface<GlobalIdentityDocuments>{

    private UKIndentityDocuments uk;

    @Override
    public GlobalIdentityDocuments toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalIdentityDocuments identityDocuments = factory.createGlobalIdentityDocuments();

        if(uk != null) identityDocuments.setUK(factory.createGlobalIdentityDocumentsUK(uk.toSoap()));

        return identityDocuments;
    }
}
