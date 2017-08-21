package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.*;
import com.lindar.id3global.internal.vo.GlobalContactDetails;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class ContactDetails implements SoapInterface<GlobalContactDetails>{
    private String email;
    private String mobileNumber;
    private String telephoneNumber;

    public GlobalContactDetails toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalContactDetails contactDetails = factory.createGlobalContactDetails();

        if(StringUtils.isNotBlank(email)) {
            contactDetails.setEmail(factory.createGlobalSupportContactEmail(email));
        }

        if(StringUtils.isNotBlank(mobileNumber)) {
            GlobalMobileTelephone globalMobileTelephone = factory.createGlobalMobileTelephone();
            globalMobileTelephone.setNumber(factory.createGlobalMobileTelephoneNumber(mobileNumber));
            contactDetails.setMobileTelephone(factory.createGlobalContactDetailsMobileTelephone(globalMobileTelephone));
        }

        if(StringUtils.isNotBlank(telephoneNumber)) {
            GlobalLandTelephone globalLandTelephone = factory.createGlobalLandTelephone();
            globalLandTelephone.setNumber(factory.createGlobalLandTelephoneNumber(telephoneNumber));
            contactDetails.setLandTelephone(factory.createGlobalContactDetailsLandTelephone(globalLandTelephone));
        }

        return contactDetails;
    }
}
