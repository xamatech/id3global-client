package com.lindar.id3global.vo.identity_documents.uk;

import com.lindar.id3global.internal.vo.GlobalUKNationalInsuranceNumber;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.SoapInterface;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class UKNationalInsuranceNumber implements SoapInterface<GlobalUKNationalInsuranceNumber> {
    private String number;

    @Override
    public GlobalUKNationalInsuranceNumber toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalUKNationalInsuranceNumber nationalInsuranceNumber = factory.createGlobalUKNationalInsuranceNumber();

        if(StringUtils.isNotBlank(number)) nationalInsuranceNumber.setNumber(factory.createGlobalUKNationalInsuranceNumberNumber(number));

        return nationalInsuranceNumber;
    }
}
