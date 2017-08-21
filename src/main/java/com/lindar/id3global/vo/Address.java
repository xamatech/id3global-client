package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalAddress;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class Address implements SoapInterface<GlobalAddress>{
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String addressLine6;
    private String addressLine7;
    private String addressLine8;
    private String zipPostcode;
    private String country;

    @Override
    public GlobalAddress toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalAddress address = factory.createGlobalAddress();

        if(StringUtils.isNotBlank(addressLine1)){
            address.setAddressLine1(factory.createGlobalAddressAddressLine1(addressLine1));
        }

        if(StringUtils.isNotBlank(addressLine2)){
            address.setAddressLine2(factory.createGlobalAddressAddressLine2(addressLine2));
        }

        if(StringUtils.isNotBlank(addressLine3)){
            address.setAddressLine3(factory.createGlobalAddressAddressLine3(addressLine3));
        }

        if(StringUtils.isNotBlank(addressLine4)){
            address.setAddressLine4(factory.createGlobalAddressAddressLine4(addressLine4));
        }

        if(StringUtils.isNotBlank(addressLine5)){
            address.setAddressLine5(factory.createGlobalAddressAddressLine5(addressLine5));
        }

        if(StringUtils.isNotBlank(addressLine6)){
            address.setAddressLine6(factory.createGlobalAddressAddressLine6(addressLine6));
        }

        if(StringUtils.isNotBlank(addressLine7)){
            address.setAddressLine7(factory.createGlobalAddressAddressLine7(addressLine7));
        }

        if(StringUtils.isNotBlank(addressLine8)){
            address.setAddressLine8(factory.createGlobalAddressAddressLine8(addressLine8));
        }

        if(StringUtils.isNotBlank(zipPostcode)){
            address.setZipPostcode(factory.createGlobalAddressZipPostcode(zipPostcode));
        }

        if(StringUtils.isNotBlank(country)){
            address.setCountry(factory.createGlobalAddressCountry(country));
        }

        return address;
    }
}
