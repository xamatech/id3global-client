package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfGlobalItemCheckResultCodes;
import com.lindar.id3global.internal.vo.GlobalAuthenticationDetails;
import com.lindar.id3global.internal.vo.GlobalProfileState;
import com.lindar.id3global.vo.requests.InputData;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuthenticationDetailsResponse {

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
    private Boolean isMultipleProfile;

    private InputData inputData;
    private List<ItemCheckResultCodes> resultCodes;
    private String name;
    private String username;
    private String domainName;
    private Long userBreakpoint;
    private Boolean noRetry;

    public static AuthenticationDetailsResponse from(GlobalAuthenticationDetails value){
        AuthenticationDetailsResponse result = new AuthenticationDetailsResponse();

        if(value.getAuthenticationID() != null) result.setAuthenticationID(value.getAuthenticationID());
        if(value.getChainID() != null) result.setChainID(value.getChainID().getValue());
        if(value.getTimestamp() != null) result.setTimestamp(value.getTimestamp().toGregorianCalendar().getTime());
        if(value.getCustomerRef() != null) result.setCustomerRef(value.getCustomerRef().getValue());
        if(value.getOrgID() != null) result.setOrgID(value.getOrgID());
        if(value.getAccountID() != null) result.setAccountID(value.getAccountID());
        if(value.getScore() != null) result.setScore(value.getScore().getValue());
        if(value.getBandText() != null) result.setBandText(value.getBandText().getValue());
        if(value.getProfileID() != null) result.setProfileID(value.getProfileID());
        if(value.getProfileName() != null) result.setProfileName(value.getProfileName().getValue());
        if(value.getProfileVersion() != null) result.setProfileVersion(value.getProfileVersion());
        if(value.getProfileRevision() != null) result.setProfileRevision(value.getProfileRevision());
        if(value.getProfileState() != null) result.setProfileState(value.getProfileState());
        if(value.isHelpdeskAccess() != null) result.setHelpdeskAccess(value.isHelpdeskAccess());
        if(value.isIsMultipleProfile() != null) result.setIsMultipleProfile(value.isIsMultipleProfile());
        if(value.getName() != null) result.setName(value.getName().getValue());
        if(value.getUsername() != null) result.setUsername(value.getUsername().getValue());
        if(value.getDomainName() != null) result.setDomainName(value.getDomainName().getValue());
        if(value.getUserBreakpoint() != null) result.setUserBreakpoint(value.getUserBreakpoint());
        if(value.isNoRetry() != null) result.setNoRetry(value.isNoRetry());

        if(value.getInputData() != null) result.setInputData(InputData.from(value.getInputData().getValue()));
        if(value.getResultCodes() != null) result.setResultCodes(transformToItemList(value.getResultCodes().getValue()));

        return result;
    }

    private static List<ItemCheckResultCodes> transformToItemList(ArrayOfGlobalItemCheckResultCodes from){
        return from.getGlobalItemCheckResultCodes().stream().map(ItemCheckResultCodes::from).collect(Collectors.toList());
    }
}
