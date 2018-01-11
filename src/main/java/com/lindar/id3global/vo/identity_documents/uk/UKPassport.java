package com.lindar.id3global.vo.identity_documents.uk;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalUKPassport;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.SoapInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UKPassport implements SoapInterface<GlobalUKPassport> {
    private String number;
    private Integer expiryDay;
    private Integer expiryMonth;
    private Integer expiryYear;

    @Override
    public GlobalUKPassport toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalUKPassport passport = factory.createGlobalUKPassport();

        if(StringUtils.isNotBlank(number)) passport.setNumber(factory.createGlobalUKPassportNumber(number));
        if(expiryDay != null) passport.setExpiryDay(factory.createGlobalUKPassportExpiryDay(expiryDay));
        if(expiryMonth != null) passport.setExpiryMonth(factory.createGlobalUKPassportExpiryMonth(expiryMonth));
        if(expiryYear != null) passport.setExpiryYear(factory.createGlobalUKPassportExpiryYear(expiryYear));

        return passport;
    }

    public static UKPassport from(GlobalUKPassport value){
        UKPassport result = new UKPassport();

        SoapUtils.populate(value.getNumber(), result::setNumber);
        SoapUtils.populate(value.getExpiryDay(), result::setExpiryDay);
        SoapUtils.populate(value.getExpiryMonth(), result::setExpiryMonth);
        SoapUtils.populate(value.getExpiryYear(), result::setExpiryYear);

        return result;
    }
}
