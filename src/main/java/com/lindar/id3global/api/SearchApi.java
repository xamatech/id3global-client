package com.lindar.id3global.api;

import com.lindar.id3global.enums.AuthenticationsSearchType;
import com.lindar.id3global.enums.AuthenticationsSortType;
import com.lindar.id3global.schema.*;
import com.lindar.id3global.support.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.support.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.vo.AccessCredentials;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class SearchApi extends BaseApi {

    private static final String ENDPOINT = "/Soap11_Stream";
    private static final String AUTHENTICATION_DETAILS_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalSearch/GetAuthenticationDetails";
    private static final String PEP_INTELLIGENCE_DATA_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalSearch/GetPEPIntelligenceData";
    private static final String AUTHENTICATIONS_ACTION = "http://www.id3global.com/ID3gWS/2013/04/IGlobalSearch/GetAuthentications";

    private final WebServiceMessageCallback AUTHENTICATION_DETAILS_CALLBACK;
    private final WebServiceMessageCallback PEP_INTELLIGENCE_DATA_CALLBACK;
    private final WebServiceMessageCallback AUTHENTICATIONS_CALLBACK;

    public SearchApi(AccessCredentials accessCredentials, WebServiceTemplate webServiceTemplate, WSSESecurityHeaderRequestWebServiceMessageCallback authenticationCallback) {
        super(ENDPOINT, accessCredentials, webServiceTemplate);

        this.AUTHENTICATION_DETAILS_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATION_DETAILS_ACTION)));
        this.PEP_INTELLIGENCE_DATA_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(PEP_INTELLIGENCE_DATA_ACTION)));
        this.AUTHENTICATIONS_CALLBACK =  new DelegatingWebServiceMessageCallback(Arrays.asList(authenticationCallback, new SoapActionCallback(AUTHENTICATIONS_ACTION)));
    }

    public GlobalAuthenticationDetails getAuthenticationDetails(@NonNull String authenticationId) {
        return getAuthenticationDetails(authenticationId, accessCredentials.getOrgId());
    }

    public GlobalAuthenticationDetails getAuthenticationDetails(@NonNull String authenticationId, @NonNull String orgId){
        GetAuthenticationDetails request = new GetAuthenticationDetails();
        if(StringUtils.isNotBlank(authenticationId)) request.setAuthenticationID(authenticationId);
        if(StringUtils.isNotBlank(orgId)) request.setOrgID(orgId);

        return ((GetAuthenticationDetailsResponse) marshalSendAndReceive(request, AUTHENTICATION_DETAILS_CALLBACK)).getGetAuthenticationDetailsResult();
    }

    public GlobalPEPIntelligenceData getPEPIntelligenceData(@NonNull String sanctionId) {
        return getPEPIntelligenceData(sanctionId, accessCredentials.getOrgId());
    }

    public GlobalPEPIntelligenceData getPEPIntelligenceData(@NonNull String sanctionId, @NonNull String orgId){
        GetPEPIntelligenceData request = new GetPEPIntelligenceData();
        if(StringUtils.isNotBlank(sanctionId)) request.setSanctionID(sanctionId);
        if(StringUtils.isNotBlank(orgId)) request.setOrgID(orgId);

        return ((GetPEPIntelligenceDataResponse) marshalSendAndReceive(request, PEP_INTELLIGENCE_DATA_CALLBACK)).getGetPEPIntelligenceDataResult();
    }

    public GlobalAuthentications getAuthentications(ZonedDateTime startDate, ZonedDateTime endDate, AuthenticationsSearchType searchType, String searchValue, long page, long pageSize, AuthenticationsSortType sortType, boolean descendingOrder) {
        return getAuthentications(startDate, endDate, searchType, searchValue, page, pageSize, sortType, descendingOrder, accessCredentials.getOrgId());
    }

    public GlobalAuthentications getAuthentications(ZonedDateTime startDate, ZonedDateTime endDate, AuthenticationsSearchType searchType, String searchValue, long page, long pageSize, AuthenticationsSortType sortType, boolean descendingOrder, String orgId){

        GetAuthentications request = new GetAuthentications();
        request.setStartDate(startDate.toInstant());
        request.setEndDate(endDate.toInstant());
        request.setSearchType(searchType.getValue());
        request.setSearchValue(searchValue);
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortType(sortType.getValue());
        request.setDescendingOrder(descendingOrder);
        request.setOrgID(orgId);

        GetAuthenticationsResponse getAuthenticationsResponse = (GetAuthenticationsResponse) marshalSendAndReceive(request, AUTHENTICATIONS_CALLBACK);
        return getAuthenticationsResponse.getGetAuthenticationsResult();
    }
}
