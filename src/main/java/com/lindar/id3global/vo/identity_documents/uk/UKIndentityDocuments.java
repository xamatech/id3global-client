package com.lindar.id3global.vo.identity_documents.uk;

import com.lindar.id3global.internal.vo.GlobalUKData;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.SoapInterface;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UKIndentityDocuments implements SoapInterface<GlobalUKData>{

    private UKPassport passport;
    private UKNationalInsuranceNumber nationalInsuranceNumber;
    private UKDrivingLicence drivingLicence;

    @Override
    public GlobalUKData toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalUKData documents = factory.createGlobalUKData();

        if(passport != null) documents.setPassport(factory.createGlobalUKDataPassport(passport.toSoap()));
        if(nationalInsuranceNumber != null) documents.setNationalInsuranceNumber(factory.createGlobalUKDataNationalInsuranceNumber(nationalInsuranceNumber.toSoap()));
        if(drivingLicence != null) documents.setDrivingLicence(factory.createGlobalUKDataDrivingLicence(drivingLicence.toSoap()));

        return documents;
    }
}
