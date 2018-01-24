package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalSanctionsDate;
import com.lindar.id3global.internal.vo.GlobalSanctionsDateType;
import lombok.Data;

@Data
public class SanctionsDate {
    private GlobalSanctionsDateType dateType;
    private Integer day;
    private Integer month;
    private Integer year;

    public static SanctionsDate from(GlobalSanctionsDate value){
        SanctionsDate result = new SanctionsDate();

        SoapUtils.populate(value::getDateType, result::setDateType);
        SoapUtils.populate(value.getDay(), result::setDay);
        SoapUtils.populate(value.getMonth(), result::setMonth);
        SoapUtils.populate(value.getYear(), result::setYear);

        return result;
    }
}
