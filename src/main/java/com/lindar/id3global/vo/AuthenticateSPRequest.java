package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.*;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class AuthenticateSPRequest implements SoapInterface<AuthenticateSP> {

    private ProfileVersion profileVersion;

    private IdentityDocuments identityDocuments;

    private String customerReference;
    private Address currentAddress;
    private ContactDetails contactDetails;
    private PersonalDetails personalDetails;

    @Override
    public AuthenticateSP toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        AuthenticateSP authenticateSP = factory.createAuthenticateSP();

        if(profileVersion != null) authenticateSP.setProfileIDVersion(factory.createAuthenticateSPProfileIDVersion(profileVersion.toSoap()));

        if(StringUtils.isNotBlank(customerReference)) authenticateSP.setCustomerReference(factory.createAuthenticateSPCustomerReference(customerReference));

        GlobalInputData globalInputData = factory.createGlobalInputData();
        GlobalAddresses globalAddresses = factory.createGlobalAddresses();

        if(currentAddress != null) globalAddresses.setCurrentAddress(factory.createGlobalAddressesCurrentAddress(currentAddress.toSoap()));

        globalInputData.setAddresses(factory.createGlobalInputDataAddresses(globalAddresses));

        GlobalPersonal globalPersonal = factory.createGlobalPersonal();

        if(personalDetails != null) globalPersonal.setPersonalDetails(factory.createGlobalPersonalPersonalDetails(personalDetails.toSoap()));

        globalInputData.setPersonal(factory.createGlobalInputDataPersonal(globalPersonal));

        if(contactDetails != null) globalInputData.setContactDetails(factory.createGlobalInputDataContactDetails(contactDetails.toSoap()));

        if(identityDocuments != null) globalInputData.setIdentityDocuments(factory.createGlobalInputDataIdentityDocuments(identityDocuments.toSoap()));

        authenticateSP.setInputData(factory.createAuthenticateSPInputData(globalInputData));

        return authenticateSP;
    }
}
