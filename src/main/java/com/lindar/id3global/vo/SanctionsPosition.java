package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalSanctionsPosition;
import lombok.Data;

@Data
public class SanctionsPosition {
    private String position;
    private String country;

    public static SanctionsPosition from(GlobalSanctionsPosition value){
        SanctionsPosition result = new SanctionsPosition();

        SoapUtils.populate(value.getPosition(), result::setPosition);
        SoapUtils.populate(value.getCountry(), result::setCountry);

        return result;
    }
}
