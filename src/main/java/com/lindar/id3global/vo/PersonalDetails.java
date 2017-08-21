package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalGender;
import com.lindar.id3global.internal.vo.GlobalPersonalDetails;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class PersonalDetails implements SoapInterface<GlobalPersonalDetails> {
    private String title;
    private String forename;
    private String middleName;
    private String surname;
    private Gender gender;
    private Integer dobDay;
    private Integer dobMonth;
    private Integer dobYear;

    @Override
    public GlobalPersonalDetails toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalPersonalDetails personalDetails = factory.createGlobalPersonalDetails();

        if(StringUtils.isNotBlank(title)) personalDetails.setTitle(factory.createGlobalPersonalDetailsTitle(title));
        if(StringUtils.isNotBlank(forename)) personalDetails.setForename(factory.createGlobalPersonalDetailsForename(forename));
        if(StringUtils.isNotBlank(middleName)) personalDetails.setMiddleName(factory.createGlobalPersonalDetailsMiddleName(middleName));
        if(StringUtils.isNotBlank(surname)) personalDetails.setSurname(factory.createGlobalPersonalDetailsSurname(surname));

        if(dobDay != null) personalDetails.setDOBDay(factory.createGlobalPersonalDetailsDOBDay(dobDay));
        if(dobMonth != null) personalDetails.setDOBMonth(factory.createGlobalPersonalDetailsDOBMonth(dobMonth));
        if(dobYear != null) personalDetails.setDOBYear(factory.createGlobalPersonalDetailsDOBYear(dobYear));

        if(gender != null) {
            switch (gender){
                case UNSPECIFIED:
                    personalDetails.setGender(factory.createGlobalPersonalDetailsGender(GlobalGender.UNSPECIFIED));
                    break;
                case UNKNOWN:
                    personalDetails.setGender(factory.createGlobalPersonalDetailsGender(GlobalGender.UNKNOWN));
                    break;
                case MALE:
                    personalDetails.setGender(factory.createGlobalPersonalDetailsGender(GlobalGender.MALE));
                    break;
                case FEMALE:
                    personalDetails.setGender(factory.createGlobalPersonalDetailsGender(GlobalGender.FEMALE));
                    break;
            }
        }

        return personalDetails;
    }
}
