package com.lindar.id3global.vo.requests;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalInputData;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InputData implements SoapInterface<GlobalInputData>{
    private IdentityDocuments identityDocuments;
    private Addresses addresses;
    private ContactDetails contactDetails;
    private Personal personal;

    @Override
    public GlobalInputData toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalInputData inputData = factory.createGlobalInputData();

        if(addresses != null) inputData.setAddresses(factory.createGlobalInputDataAddresses(addresses.toSoap()));
        if(personal != null) inputData.setPersonal(factory.createGlobalInputDataPersonal(personal.toSoap()));
        if(contactDetails != null) inputData.setContactDetails(factory.createGlobalInputDataContactDetails(contactDetails.toSoap()));
        if(identityDocuments != null) inputData.setIdentityDocuments(factory.createGlobalInputDataIdentityDocuments(identityDocuments.toSoap()));

        return inputData;
    }

    public static InputData from(GlobalInputData value) {
        InputData result = new InputData();

        SoapUtils.populate(value.getAddresses(), Addresses::from, result::setAddresses);
        SoapUtils.populate(value.getPersonal(), Personal::from, result::setPersonal);
        SoapUtils.populate(value.getIdentityDocuments(), IdentityDocuments::from, result::setIdentityDocuments);

        return result;
    }
}
