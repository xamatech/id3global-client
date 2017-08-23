package com.lindar.id3global.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateRequest {

    protected IdentityDocuments identityDocuments;

    protected String customerReference;
    protected Address currentAddress;
    protected ContactDetails contactDetails;
    protected PersonalDetails personalDetails;
}
