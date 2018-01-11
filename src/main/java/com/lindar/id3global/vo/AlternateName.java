package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalAlternateName;
import com.lindar.id3global.internal.vo.GlobalGender;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlternateName implements SoapInterface<GlobalAlternateName>{

    private String title;
    private String forename;
    private String middleName;
    private String surname;
    private GlobalGender gender;
    private Integer periodInUseStartDay;
    private Integer periodInUseStartMonth;
    private Integer periodInUseStartYear;
    private Integer periodInUseEndDay;
    private Integer periodInUseEndMonth;
    private Integer periodInUseEndYear;
    private String documentType;

    @Override
    public GlobalAlternateName toSoap() {

        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalAlternateName result = factory.createGlobalAlternateName();

        if(title != null) result.setTitle(factory.createGlobalAlternateNameTitle(title));
        if(forename != null) result.setForename(factory.createGlobalAlternateNameForename(forename));
        if(middleName != null) result.setMiddleName(factory.createGlobalAlternateNameMiddleName(middleName));
        if(surname != null) result.setSurname(factory.createGlobalAlternateNameSurname(surname));
        if(gender != null) result.setGender(factory.createGlobalAlternateNameGender(gender));
        if(periodInUseStartDay != null) result.setPeriodInUseStartDay(factory.createGlobalAlternateNamePeriodInUseStartDay(periodInUseStartDay));
        if(periodInUseStartMonth != null) result.setPeriodInUseStartMonth(factory.createGlobalAlternateNamePeriodInUseStartMonth(periodInUseStartMonth));
        if(periodInUseStartYear != null) result.setPeriodInUseStartYear(factory.createGlobalAlternateNamePeriodInUseStartYear(periodInUseStartYear));
        if(periodInUseEndDay != null) result.setPeriodInUseEndDay(factory.createGlobalAlternateNamePeriodInUseEndDay(periodInUseEndDay));
        if(periodInUseEndMonth != null) result.setPeriodInUseEndMonth(factory.createGlobalAlternateNamePeriodInUseEndMonth(periodInUseEndMonth));
        if(periodInUseEndYear != null) result.setPeriodInUseEndYear(factory.createGlobalAlternateNamePeriodInUseEndYear(periodInUseEndYear));
        if(documentType != null) result.setDocumentType(factory.createGlobalAlternateNameDocumentType(documentType));

        return result;
    }

    public static AlternateName from(GlobalAlternateName value){
        AlternateName result = new AlternateName();

        if(value.getTitle() != null) result.setTitle(value.getTitle().getValue());
        if(value.getForename() != null) result.setForename(value.getForename().getValue());
        if(value.getMiddleName() != null) result.setMiddleName(value.getMiddleName().getValue());
        if(value.getSurname() != null) result.setSurname(value.getSurname().getValue());
        if(value.getGender() != null) result.setGender(value.getGender().getValue());
        if(value.getPeriodInUseStartDay() != null) result.setPeriodInUseStartDay(value.getPeriodInUseStartDay().getValue());
        if(value.getPeriodInUseStartMonth() != null) result.setPeriodInUseStartMonth(value.getPeriodInUseStartMonth().getValue());
        if(value.getPeriodInUseStartYear() != null) result.setPeriodInUseStartYear(value.getPeriodInUseStartYear().getValue());
        if(value.getPeriodInUseEndDay() != null) result.setPeriodInUseEndDay(value.getPeriodInUseEndDay().getValue());
        if(value.getPeriodInUseEndMonth() != null) result.setPeriodInUseEndMonth(value.getPeriodInUseEndMonth().getValue());
        if(value.getPeriodInUseEndYear() != null) result.setPeriodInUseEndYear(value.getPeriodInUseEndYear().getValue());
        if(value.getDocumentType() != null) result.setDocumentType(value.getDocumentType().getValue());

        return result;
    }
}
