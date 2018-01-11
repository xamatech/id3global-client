package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfstring;
import com.lindar.id3global.internal.vo.GlobalGender;
import com.lindar.id3global.internal.vo.GlobalPersonalDetails;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalDetails implements SoapInterface<GlobalPersonalDetails> {
    protected String title;
    protected String forename;
    protected String middleName;
    protected String surname;
    protected GlobalGender gender;
    protected Integer dobDay;
    protected Integer dobMonth;
    protected Integer dobYear;
    protected String countryOfBirth;
    protected String secondSurname;
    protected List<String> additionalMiddleNames;

    @Override
    public GlobalPersonalDetails toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalPersonalDetails personalDetails = factory.createGlobalPersonalDetails();

        if(StringUtils.isNotBlank(title)) personalDetails.setTitle(factory.createGlobalPersonalDetailsTitle(title));
        if(StringUtils.isNotBlank(forename)) personalDetails.setForename(factory.createGlobalPersonalDetailsForename(forename));
        if(StringUtils.isNotBlank(middleName)) personalDetails.setMiddleName(factory.createGlobalPersonalDetailsMiddleName(middleName));
        if(StringUtils.isNotBlank(surname)) personalDetails.setSurname(factory.createGlobalPersonalDetailsSurname(surname));
        if(gender != null) personalDetails.setGender(factory.createGlobalPersonalDetailsGender(gender));
        if(dobDay != null) personalDetails.setDOBDay(factory.createGlobalPersonalDetailsDOBDay(dobDay));
        if(dobMonth != null) personalDetails.setDOBMonth(factory.createGlobalPersonalDetailsDOBMonth(dobMonth));
        if(dobYear != null) personalDetails.setDOBYear(factory.createGlobalPersonalDetailsDOBYear(dobYear));
        if(StringUtils.isNotBlank(countryOfBirth)) personalDetails.setCountryOfBirth(factory.createGlobalPersonalDetailsCountryOfBirth(countryOfBirth));
        if(StringUtils.isNotBlank(secondSurname)) personalDetails.setSecondSurname(factory.createGlobalPersonalDetailsSecondSurname(secondSurname));
        if(additionalMiddleNames != null && !additionalMiddleNames.isEmpty()) personalDetails.setAdditionalMiddleNames(factory.createGlobalPersonalDetailsAdditionalMiddleNames(new ArrayOfstring(additionalMiddleNames)));

        return personalDetails;
    }

    public static PersonalDetails from(GlobalPersonalDetails value){
        PersonalDetails result = new PersonalDetails();

        if(value.getTitle() != null) result.setTitle(value.getTitle().getValue());
        if(value.getForename() != null) result.setForename(value.getForename().getValue());
        if(value.getMiddleName() != null) result.setMiddleName(value.getMiddleName().getValue());
        if(value.getSurname() != null) result.setSurname(value.getSurname().getValue());
        if(value.getGender() != null) result.setGender(value.getGender().getValue());
        if(value.getDOBDay() != null) result.setDobDay(value.getDOBDay().getValue());
        if(value.getDOBMonth() != null) result.setDobMonth(value.getDOBMonth().getValue());
        if(value.getDOBYear() != null) result.setDobYear(value.getDOBYear().getValue());
        if(value.getCountryOfBirth() != null) result.setCountryOfBirth(value.getCountryOfBirth().getValue());
        if(value.getSecondSurname() != null) result.setSecondSurname(value.getSecondSurname().getValue());
        if(value.getAdditionalMiddleNames() != null && !value.getAdditionalMiddleNames().isNil()) result.setAdditionalMiddleNames(value.getAdditionalMiddleNames().getValue().getString());

        return result;
    }
}
