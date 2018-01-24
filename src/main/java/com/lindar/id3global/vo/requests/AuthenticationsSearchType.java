package com.lindar.id3global.vo.requests;

public enum AuthenticationsSearchType {
    AUTHENTICATION_ID(1), // Authentication ID
    CUSTOMER_REFERENCE(2), // Customer Reference
    DECISION_BAND(3), // Decision Band
    PROFILE_ID(4), // Profile ID
    PROFILE_NAME(5), // Profile Name
    PROFILE_STATE(6), // Profile State
    CHAIN_ID(7), // Chain ID
    USERNAME(8), // Username
    ACCOUNT_ID(9), // Account ID
    HELPDESK_ACCESS(10), // Helpdesk Access
    DEVICE_ID(11), // Device ID
    FIRST_INITIAL_FORENAME_AND_SURNAME_AND_POSTCODE(12), // First Initial Forename + Surname + Postcode (delimited with |)
    FORENAME_AND_SURNAME_AND_POSTCODE(13), // Forename + Surname + Postcode (delimited with |)
    CREDIT_DEBIT_CARD(14), // Credit/Debit Card (matches first 6 and last 4 digits only)
    TELEPHONE_NUMBER(15), // Telephone Number
    DRIVING_LICENCE_NUMBER(16), // Driving Licence Number
    UK_PASSPORT_NUMBER(17), // UK Passport Number
    INTERNATIONAL_PASSPORT_NUMBER(18), // International Passport Number
    DOB_AND_POSTCODE(19), // Date of Birth (DDMMYYYY) + Postcode
    SURNAME(20), // Surname
    FORENAME_SURNAME(21), // Forename + Surname (delimited with |)
    FIRST_INITIAL_OF_FORENAME_AND_SURNAME(22), // First Initial of Forename + Surname (delimited with |)
    FORENAME_AND_FIRST_INITIAL_OF_SURNAME(23), // Forename + First Initial of Surname (delimited with |)
    IDENTITY_CARD_NUMBER(24); // Identity Card Number

    private final long value;

    AuthenticationsSearchType(long value){
        this.value = value;
    }

    public long getValue(){
        return value;
    }
}
