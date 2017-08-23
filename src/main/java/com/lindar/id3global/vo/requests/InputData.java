package com.lindar.id3global.vo.requests;

import com.lindar.id3global.internal.vo.GlobalAddresses;
import com.lindar.id3global.internal.vo.GlobalInputData;
import com.lindar.id3global.internal.vo.GlobalPersonal;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputData implements SoapInterface<GlobalInputData>{
    private IdentityDocuments identityDocuments;
    private Address currentAddress;
    private ContactDetails contactDetails;
    private PersonalDetails personalDetails;

    @Override
    public GlobalInputData toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalInputData inputData = factory.createGlobalInputData();
        GlobalAddresses globalAddresses = factory.createGlobalAddresses();

        if(currentAddress != null) globalAddresses.setCurrentAddress(factory.createGlobalAddressesCurrentAddress(currentAddress.toSoap()));

        inputData.setAddresses(factory.createGlobalInputDataAddresses(globalAddresses));

        GlobalPersonal globalPersonal = factory.createGlobalPersonal();

        if(personalDetails != null) globalPersonal.setPersonalDetails(factory.createGlobalPersonalPersonalDetails(personalDetails.toSoap()));

        inputData.setPersonal(factory.createGlobalInputDataPersonal(globalPersonal));

        if(contactDetails != null) inputData.setContactDetails(factory.createGlobalInputDataContactDetails(contactDetails.toSoap()));

        if(identityDocuments != null) inputData.setIdentityDocuments(factory.createGlobalInputDataIdentityDocuments(identityDocuments.toSoap()));

        return inputData;
    }
}
