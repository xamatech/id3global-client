package com.lindar.id3global.api;

import com.lindar.id3global.internal.callbacks.DelegatingWebServiceMessageCallback;
import com.lindar.id3global.internal.callbacks.WSSESecurityHeaderRequestWebServiceMessageCallback;
import com.lindar.id3global.internal.vo.*;
import com.lindar.id3global.vo.AccessCredentials;
import com.lindar.id3global.vo.AuthenticationDetailsResponse;
import com.lindar.id3global.vo.Authentications;
import com.lindar.id3global.vo.PEPIntelligenceData;
import com.lindar.id3global.vo.requests.AuthenticationsSearchType;
import com.lindar.id3global.vo.requests.AuthenticationsSortType;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

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

    public AuthenticationDetailsResponse getAuthenticationDetails(@NonNull String authenticationId) {
        return getAuthenticationDetails(authenticationId, accessCredentials.getOrgId());
    }

    public AuthenticationDetailsResponse getAuthenticationDetails(@NonNull String authenticationId, @NonNull String orgId){
        GetAuthenticationDetails request = buildGetAuthenticationDetails(authenticationId, orgId);
        GetAuthenticationDetailsResponse response = (GetAuthenticationDetailsResponse) marshalSendAndReceive(request, AUTHENTICATION_DETAILS_CALLBACK);
        GlobalAuthenticationDetails value = response.getGetAuthenticationDetailsResult().getValue();

        return AuthenticationDetailsResponse.from(value);
    }

    public PEPIntelligenceData getPEPIntelligenceData(@NonNull String sanctionId) {
        return getPEPIntelligenceData(sanctionId, accessCredentials.getOrgId());
    }

    public PEPIntelligenceData getPEPIntelligenceData(@NonNull String sanctionId, @NonNull String orgId){
        GetPEPIntelligenceData request = buildPEPIntelligenceData(sanctionId, orgId);
        GetPEPIntelligenceDataResponse response = (GetPEPIntelligenceDataResponse) marshalSendAndReceive(request, PEP_INTELLIGENCE_DATA_CALLBACK);
        GlobalPEPIntelligenceData value = response.getGetPEPIntelligenceDataResult().getValue();
        return PEPIntelligenceData.from(value);
    }

    public Authentications getAuthentications(Date startDate, Date endDate, AuthenticationsSearchType searchType, String searchValue, int page, int pageSize, AuthenticationsSortType sortType, boolean descendingOrder) {
        return getAuthentications(startDate, endDate, searchType, searchValue, page, pageSize, sortType, descendingOrder, accessCredentials.getOrgId());
    }

    public Authentications getAuthentications(Date startDate, Date endDate, AuthenticationsSearchType searchType, String searchValue, int page, int pageSize, AuthenticationsSortType sortType, boolean descendingOrder, String orgId){
        GetAuthentications request = buildGetAuthentications(startDate, endDate, searchType, searchValue, page, pageSize, sortType, descendingOrder, orgId);
        GetAuthenticationsResponse response = (GetAuthenticationsResponse) marshalSendAndReceive(request, AUTHENTICATIONS_CALLBACK);
        GlobalAuthentications value = response.getGetAuthenticationsResult().getValue();
        return Authentications.from(value);
    }

    private GetAuthenticationDetails buildGetAuthenticationDetails(String authenticationId, String orgId){
        GetAuthenticationDetails getAuthenticationDetails = factory.createGetAuthenticationDetails();
        if(StringUtils.isNotBlank(authenticationId)) getAuthenticationDetails.setAuthenticationID(authenticationId);
        if(StringUtils.isNotBlank(orgId)) getAuthenticationDetails.setOrgID(orgId);
        return getAuthenticationDetails;
    }

    private GetPEPIntelligenceData buildPEPIntelligenceData(String sanctionId, String orgId){
        GetPEPIntelligenceData request = factory.createGetPEPIntelligenceData();
        if(StringUtils.isNotBlank(sanctionId)) request.setSanctionID(factory.createGetPEPIntelligenceDataSanctionID(sanctionId));
        if(StringUtils.isNotBlank(orgId)) request.setOrgID(orgId);
        return request;
    }

    private GetAuthentications buildGetAuthentications(Date startDate, Date endDate, AuthenticationsSearchType searchType, String searchValue, long page, long pageSize, AuthenticationsSortType sortType, boolean descendingOrder, String orgId){
        GetAuthentications request = factory.createGetAuthentications();
        request.setStartDate(convertDate(startDate));
        request.setEndDate(convertDate(endDate));
        request.setSearchType(searchType.getValue());
        request.setSearchValue(factory.createGetAuthenticationsSearchValue(searchValue));
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setSortType(sortType.getValue());
        request.setDescendingOrder(descendingOrder);
        request.setOrgID(orgId);
        return request;
    }

    public XMLGregorianCalendar convertDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String dateS = df.format(date);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(dateS);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }
}
