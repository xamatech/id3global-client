package com.lindar.id3global.vo.identity_documents.uk;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalUKNationalInsuranceNumber;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.SoapInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public static UKNationalInsuranceNumber from(GlobalUKNationalInsuranceNumber value){
        UKNationalInsuranceNumber result = new UKNationalInsuranceNumber();

        SoapUtils.populate(value.getNumber(), result::setNumber);

        return result;
    }

}
