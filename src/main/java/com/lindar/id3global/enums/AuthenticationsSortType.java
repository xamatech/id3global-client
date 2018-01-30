package com.lindar.id3global.enums;

public enum AuthenticationsSortType {
    CUSTOMER_REFERENCE(1), TIMESTAMP(2), PROFILE_NAME(3), DECISION_BAND(4), SCORE(5);

    private final long value;

    AuthenticationsSortType(long value){
        this.value = value;
    }

    public long getValue(){
        return value;
    }
}
