package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalSanctionsAddress;
import lombok.Data;

@Data
public class SanctionsAddress {
    private String addressLine;
    private String city;
    private String country;
    private String region;

    public static SanctionsAddress from(GlobalSanctionsAddress value){
        SanctionsAddress result = new SanctionsAddress();

        SoapUtils.populate(value.getAddressLine(), result::setAddressLine);
        SoapUtils.populate(value.getCity(), result::setCity);
        SoapUtils.populate(value.getCountry(), result::setCountry);
        SoapUtils.populate(value.getRegion(), result::setRegion);

        return result;
    }
}
