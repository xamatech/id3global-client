package com.lindar.id3global;

import com.lindar.id3global.schema.GlobalContactDetails;
import com.lindar.id3global.schema.GlobalInputData;
import com.lindar.id3global.schema.GlobalPersonal;
import com.lindar.id3global.schema.GlobalPersonalDetails;
import com.lindar.id3global.schema.GlobalResultData;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AuthenticateApiTest {

    private ID3GlobalClient client;

    @Before
    public void before(){
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
    public void singleAuthenticate() {

        GlobalInputData inputData = new GlobalInputData();
        GlobalContactDetails contactDetails = new GlobalContactDetails();
        contactDetails.setEmail("someone@test.com");
        inputData.setContactDetails(contactDetails);

        GlobalPersonal personal = new GlobalPersonal();
        GlobalPersonalDetails personalDetails = new GlobalPersonalDetails();
        personalDetails.setForename("James");
        personalDetails.setSurname("Fake");

        personalDetails.setDOBDay(1);
        personalDetails.setDOBMonth(1);
        personalDetails.setDOBYear(1990);
        personal.setPersonalDetails(personalDetails);
        inputData.setPersonal(personal);

        GlobalResultData globalResultData = client.authenticate().singleAuthenticate("test-ref", inputData);
        System.out.println(globalResultData.getBandText());
    }
}