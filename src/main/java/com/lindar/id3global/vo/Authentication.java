package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.GlobalAuthentication;
import com.lindar.id3global.internal.vo.GlobalProfileState;
import lombok.Data;

import java.util.Date;

@Data
public class Authentication {
    private String authenticationID;
    private String chainID;
    private Date timestamp;
    private String customerRef;
    private String orgID;
    private String accountID;
    private Integer score;
    private String bandText;
    private String profileID;
    private String profileName;
    private Long profileVersion;
    private Long profileRevision;
    private GlobalProfileState profileState;
    private Boolean helpdeskAccess;
    private Boolean multipleProfile;

    public static Authentication from(GlobalAuthentication value){
        Authentication result = new Authentication();

        SoapUtils.populate(value::getAuthenticationID, result::setAuthenticationID);
        SoapUtils.populate(value.getChainID(), result::setChainID);
        SoapUtils.populate(value.getTimestamp(), result::setTimestamp);
        SoapUtils.populate(value.getCustomerRef(), result::setCustomerRef);
        SoapUtils.populate(value::getOrgID, result::setOrgID);
        SoapUtils.populate(value::getAccountID, result::setAccountID);
        SoapUtils.populate(value.getScore(), result::setScore);
        SoapUtils.populate(value.getBandText(), result::setBandText);
        SoapUtils.populate(value::getProfileID, result::setProfileID);
        SoapUtils.populate(value.getProfileName(), result::setProfileName);
        SoapUtils.populate(value::getProfileVersion, result::setProfileVersion);
        SoapUtils.populate(value::getProfileRevision, result::setProfileRevision);
        SoapUtils.populate(value::getProfileState, result::setProfileState);
        SoapUtils.populate(value::isHelpdeskAccess, result::setHelpdeskAccess);
        SoapUtils.populate(value::isIsMultipleProfile, result::setMultipleProfile);

        return result;
    }
}
