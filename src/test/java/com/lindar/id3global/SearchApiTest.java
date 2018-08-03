package com.lindar.id3global;

import com.lindar.id3global.enums.AuthenticationsSearchType;
import com.lindar.id3global.enums.AuthenticationsSortType;
import com.lindar.id3global.schema.GlobalAuthenticationDetails;
import com.lindar.id3global.schema.GlobalAuthentications;
import com.lindar.id3global.schema.GlobalPEPIntelligenceData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Ignore
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
    }

    @Test
    public void testCheckCredentials() {
        GlobalAuthenticationDetails response = client.search().getAuthenticationDetails("0898241f-f7c2-40e8-841b-f6f3e4dab823");
        Assert.assertTrue(response.getOrgID() != null);
    }

    @Test
    public void testPEPIntelligenceData() {
        GlobalPEPIntelligenceData response = client.search().getPEPIntelligenceData("300157088");
        Assert.assertTrue(response.getFullname() != null);
    }

    @Test
    public void testGetAuthentications() {
        GlobalAuthentications response = client.search().getAuthentications(
                ZonedDateTime.now(ZoneOffset.UTC).minusDays(30),
                ZonedDateTime.now(ZoneOffset.UTC),
                AuthenticationsSearchType.CUSTOMER_REFERENCE,
                "steven-test-ref",
                1, 100,
                AuthenticationsSortType.TIMESTAMP, true
        );
        Assert.assertTrue(response.getTotalAuthentications() > 0);
    }
}
