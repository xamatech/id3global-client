package com.lindar.id3global;

import com.lindar.id3global.schema.GlobalAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CredentialsApiTest {

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
        GlobalAccount account = client.credentials().checkCredentials();
        Assert.assertTrue(account.getOrgID() != null);
    }
}
