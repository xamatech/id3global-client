package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfGlobalItemCheckResultCodes;
import com.lindar.id3global.internal.vo.GlobalResultData;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuthenticateResponse extends AuthenticationSummary {


    private Long userBreakpoint;
    private Boolean noRetry;
    private String country;
    protected List<ItemCheckResultCodes> resultCodes;


    public static AuthenticateResponse from(GlobalResultData value){

        AuthenticateResponse result = new AuthenticateResponse();

        if(value.getTimestamp() != null) result.setTimestamp(value.getTimestamp().toGregorianCalendar().getTime());
        if(value.getCustomerRef() != null) result.setCustomerRef(value.getCustomerRef().getValue());
        if(value.getProfileName() != null) result.setProfileName(value.getProfileName().getValue());
        if(value.getScore() != null) result.setScore(value.getScore().getValue());
        if(value.getBandText() != null) result.setBandText(value.getBandText().getValue());
        if(value.getChainID() != null) result.setChainID(value.getChainID().getValue());
        if(value.getCountry() != null) result.setCountry(value.getCountry().getValue());

        result.setAuthenticationID(value.getAuthenticationID());
        result.setProfileID(value.getProfileID());
        result.setUserBreakpoint(value.getUserBreakpoint());
        result.setNoRetry(value.isNoRetry());
        result.setProfileVersion(value.getProfileVersion());
        result.setProfileRevision(value.getProfileRevision());

        if(value.getResultCodes() != null) result.setResultCodes(transformToItemList(value.getResultCodes().getValue()));

        return result;
    }

    private static List<ItemCheckResultCodes> transformToItemList(ArrayOfGlobalItemCheckResultCodes from){
        return from.getGlobalItemCheckResultCodes().stream().map(ItemCheckResultCodes::from).collect(Collectors.toList());
    }
}
