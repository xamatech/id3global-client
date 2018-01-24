package com.lindar.id3global;

import com.lindar.id3global.internal.vo.GlobalGender;
import com.lindar.id3global.vo.*;
import com.lindar.id3global.vo.identity_documents.uk.UKDrivingLicence;
import com.lindar.id3global.vo.identity_documents.uk.UKIndentityDocuments;
import com.lindar.id3global.vo.identity_documents.uk.UKPassport;
import com.lindar.id3global.vo.requests.InputData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationApiTest {

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
    public void testSingleAuthentication() {
        InputData inputData = InputData.builder()
                .contactDetails(
                        ContactDetails.builder()
                                .email("KyleAshton@armyspy.com")
                                .mobileNumber("07041357509")
                                .build())
                .addresses(Addresses.builder()
                                   .currentAddress(
                                           Address.builder()
                                                   .addressLine1("32 Londesborough Rd")
                                                   //.addressLine2("")
                                                   .addressLine3("Scarborough")
                                                   .addressLine4("North Yorkshire")
                                                   .zipPostcode("YO12 5AF")
                                                   .country("united kingdom")
                                                   .build()
                                   )
                                   .build())

                .personal(
                        Personal.builder()
                        .personalDetails(
                                PersonalDetails.builder()
                                        .title("mr")
                                        .forename("Kyle")
                                        .surname("Ashton")
                                        .dobDay(7)
                                        .dobMonth(4)
                                        .dobYear(1984)
                                        .gender(GlobalGender.MALE)
                                        .build()
                        )
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

        AuthenticateResponse response = client.authenticate().singleAuthenticate("customer-ref", inputData);

        Assert.assertTrue(response.getBandText() != null);
    }

    @Test
    public void testIncrementalVerification() {
        InputData.InputDataBuilder inputDataBuilder = InputData.builder()
                .contactDetails(
                        ContactDetails.builder()
                                .email("KyleAshton@armyspy.com")
                                .mobileNumber("07041357509")
                                .build())
                .addresses(Addresses.builder()
                                   .currentAddress(
                                           Address.builder()
                                                   .addressLine1("32 Londesborough Rd")
                                                   //.addressLine2("")
                                                   .addressLine3("Scarborough")
                                                   .addressLine4("North Yorkshire")
                                                   .zipPostcode("YO12 5AF")
                                                   .country("united kingdom")
                                                   .build()
                                   )
                                   .build())

                .personal(
                        Personal.builder()
                                .personalDetails(
                                        PersonalDetails.builder()
                                                .title("mr")
                                                .forename("Kyle")
                                                .surname("Ashton")
                                                .dobDay(7)
                                                .dobMonth(4)
                                                .dobYear(1984)
                                                .gender(GlobalGender.MALE)
                                                .build()
                                )
                                .build()
                );

        InputData missingInputData = inputDataBuilder.build();


        AuthenticateResponse singleAuthenticate = client.authenticate().singleAuthenticate("customer-ref", missingInputData);

        Assert.assertTrue("Profile must have a breakpoint", singleAuthenticate.getUserBreakpoint() != null);

        InputData completeInputData = inputDataBuilder.identityDocuments(
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
        ).build();

        String authenticationID = singleAuthenticate.getAuthenticationID();

        AuthenticateResponse response = client.authenticate().incrementalVerification(authenticationID, "customer-ref", completeInputData);

        Assert.assertTrue(response.getBandText() != null);
    }

    @Test
    public void testSingleAuthenticationPepSanction() {
        InputData inputData = InputData.builder()
                .personal(
                        Personal.builder()
                                .personalDetails(
                                        PersonalDetails.builder()
                                                .title("mr")
                                                .forename("David")
                                                .surname("Cameron")
                                                .dobDay(9)
                                                .dobMonth(10)
                                                .dobYear(1966)
                                                .gender(GlobalGender.MALE)
                                                .build()
                                )
                                .build()
                )


                .build();

        AuthenticateResponse response = client.authenticate().singleAuthenticate("sanction-test", inputData);

        Assert.assertTrue(response.getBandText() != null);
    }
}
