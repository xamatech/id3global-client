package com.lindar.id3global;

import com.lindar.id3global.vo.AuthenticationDetailsResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
