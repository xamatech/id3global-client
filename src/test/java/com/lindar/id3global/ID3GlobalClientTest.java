package com.lindar.id3global;

import com.lindar.id3global.vo.*;
import com.lindar.id3global.vo.identity_documents.uk.UKDrivingLicence;
import com.lindar.id3global.vo.identity_documents.uk.UKIndentityDocuments;
import com.lindar.id3global.vo.identity_documents.uk.UKPassport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ID3GlobalClientTest {

    private ID3GlobalClient client;

    @Before
    public void setup() {
        client = ID3GlobalClient.build(
                System.getenv("id3global-api-url"),
                System.getenv("id3global-username"),
                System.getenv("id3global-password"),
                System.getenv("id3global-default-profile-id"),
                Integer.valueOf(System.getenv("id3global-default-profile-version"))
        );
    }

    @Test
    public void test() {

        AuthenticateSPRequest authenticateRequest = AuthenticateSPRequest.builder()
                .customerReference("customer-ref")
                .contactDetails(
                        ContactDetails.builder()
                                .email("KyleAshton@armyspy.com")
                                .mobileNumber("07041357509")
                                .build())
                .currentAddress(
                        Address.builder()
                                .addressLine1("32 Londesborough Rd")
                                //.addressLine2("")
                                .addressLine3("Scarborough")
                                .addressLine4("North Yorkshire")
                                .zipPostcode("YO12 5AF")
                                .country("united kingdom")
                                .build())
                .personalDetails(
                        PersonalDetails.builder()
                                .title("mr")
                                .forename("Kyle")
                                .surname("Ashton")
                                .dobDay(7)
                                .dobMonth(4)
                                .dobYear(1984)
                                .gender(Gender.MALE)
                                .build()
                )

                .identityDocuments(
                        IdentityDocuments.builder()
                                .uk(UKIndentityDocuments.builder()
                                        .drivingLicence(UKDrivingLicence.builder()
                                                .postcode("YO12 5AF")
                                                .issueDay(27)
                                                .issueMonth(11)
                                                .issueYear(2015)
                                                .build())
                                        .passport(UKPassport.builder()
                                                .expiryDay(5)
                                                .build())
                                        .build())
                                .build()
                )

                .build();

        AuthenticateSPResponse response = client.authenticate().singleAuthenticate(authenticateRequest);

        Assert.assertTrue(response.getBandText() != null);
    }
}
