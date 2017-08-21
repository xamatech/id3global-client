package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalInternationalPassport;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class InternationalPassport implements SoapInterface<GlobalInternationalPassport> {
    private String number;
    private Integer expiryDay;
    private Integer expiryMonth;
    private Integer expiryYear;
    private String countryOfOrigin;
    private Integer issueDay;
    private Integer issueMonth;
    private Integer issueYear;
    private String shortPassportNumber;

    public GlobalInternationalPassport toSoap(){
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalInternationalPassport internationalPassport = factory.createGlobalInternationalPassport();

        if(StringUtils.isNotBlank(number)) internationalPassport.setNumber(factory.createGlobalInternationalPassportNumber(number));
        if(StringUtils.isNotBlank(shortPassportNumber)) internationalPassport.setShortPassportNumber(factory.createGlobalInternationalPassportShortPassportNumber(shortPassportNumber));
        if(StringUtils.isNotBlank(countryOfOrigin)) internationalPassport.setCountryOfOrigin(factory.createGlobalInternationalPassportCountryOfOrigin(countryOfOrigin));

        if(expiryDay != null) internationalPassport.setExpiryDay(factory.createGlobalInternationalPassportExpiryDay(expiryDay));
        if(expiryMonth != null) internationalPassport.setExpiryMonth(factory.createGlobalInternationalPassportExpiryMonth(expiryMonth));
        if(expiryYear != null) internationalPassport.setExpiryYear(factory.createGlobalInternationalPassportExpiryYear(expiryYear));
        if(issueDay != null) internationalPassport.setIssueDay(factory.createGlobalInternationalPassportIssueDay(issueDay));
        if(issueMonth != null) internationalPassport.setIssueMonth(factory.createGlobalInternationalPassportIssueMonth(issueMonth));
        if(issueYear != null) internationalPassport.setIssueYear(factory.createGlobalInternationalPassportIssueYear(issueYear));

        return internationalPassport;
    }
}
