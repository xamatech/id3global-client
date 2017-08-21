package com.lindar.id3global.vo.identity_documents.uk;

import com.lindar.id3global.internal.vo.GlobalUKDrivingLicence;
import com.lindar.id3global.internal.vo.ObjectFactory;
import com.lindar.id3global.vo.SoapInterface;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class UKDrivingLicence implements SoapInterface<GlobalUKDrivingLicence> {
    private String number;
    private String mailSort;
    private String microfiche;
    private String postcode;
    private Integer expiryDay;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer issueDay;
    private Integer issueMonth;
    private Integer issueYear;
    private Integer issueNumber;

    public GlobalUKDrivingLicence toSoap(){
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalUKDrivingLicence drivingLicence = factory.createGlobalUKDrivingLicence();

        if(StringUtils.isNotBlank(number)) drivingLicence.setNumber(factory.createGlobalUKDrivingLicenceNumber(number));
        if(StringUtils.isNotBlank(mailSort)) drivingLicence.setMailSort(factory.createGlobalUKDrivingLicenceMailSort(mailSort));
        if(StringUtils.isNotBlank(microfiche)) drivingLicence.setMicrofiche(factory.createGlobalUKDrivingLicenceMicrofiche(microfiche));
        if(StringUtils.isNotBlank(postcode)) drivingLicence.setPostcode(factory.createGlobalUKDrivingLicencePostcode(postcode));

        if(expiryDay != null) drivingLicence.setExpiryDay(factory.createGlobalUKDrivingLicenceExpiryDay(expiryDay));
        if(expiryMonth != null) drivingLicence.setExpiryMonth(factory.createGlobalUKDrivingLicenceExpiryMonth(expiryMonth));
        if(expiryYear != null) drivingLicence.setExpiryYear(factory.createGlobalUKDrivingLicenceExpiryYear(expiryYear));
        if(issueDay != null) drivingLicence.setIssueDay(factory.createGlobalUKDrivingLicenceIssueDay(issueDay));
        if(issueMonth != null) drivingLicence.setIssueMonth(factory.createGlobalUKDrivingLicenceIssueMonth(issueMonth));
        if(issueYear != null) drivingLicence.setIssueYear(factory.createGlobalUKDrivingLicenceIssueYear(issueYear));
        if(issueNumber != null) drivingLicence.setIssueNumber(factory.createGlobalUKDrivingLicenceIssueNumber(issueNumber));

        return drivingLicence;
    }
}
