package com.lindar.id3global;

import com.lindar.id3global.vo.AuthenticationDetailsResponse;
import com.lindar.id3global.vo.Authentications;
import com.lindar.id3global.vo.PEPIntelligenceData;
import com.lindar.id3global.vo.requests.AuthenticationsSearchType;
import com.lindar.id3global.vo.requests.AuthenticationsSortType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.ZonedDateTime;

public class SearchApiTest {

    private ID3GlobalClient client;

    @Before
    public void setup() {
        client = ID3GlobalClient.build(
                System.getenv("id3global-api-url"),
                System.getenv("id3global-username"),
                System.getenv("id3global-password"),
                System.getenv("id3global-default-profile-id"),
                Integer.valueOf(System.getenv("id3global-default-profile-version")),
                System.getenv("id3global-default-org-id")
        );

        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
    }

    @Test
    public void testCheckCredentials() {
        AuthenticationDetailsResponse response = client.search().getAuthenticationDetails("0898241f-f7c2-40e8-841b-f6f3e4dab823");
        Assert.assertTrue(response.getOrgID() != null);
    }

    @Test
    public void testPEPIntelligenceData() {
        PEPIntelligenceData response = client.search().getPEPIntelligenceData("300157088");
        Assert.assertTrue(response.getFullname() != null);
    }

    @Test
    public void testGetAuthentications() {
        Authentications response = client.search().getAuthentications(
                Date.from(ZonedDateTime.now().minusDays(30).toInstant()),
                Date.from(ZonedDateTime.now().toInstant()),
                AuthenticationsSearchType.CUSTOMER_REFERENCE,
                "pep-test",
                1, 100,
                AuthenticationsSortType.TIMESTAMP, true
        );
        Assert.assertTrue(response.getTotalAuthentications() > 0);
    }
}
