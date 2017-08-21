package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalMatch;

public enum MatchEnum {
    NA,
    MATCH,
    MISMATCH,
    NOMATCH;

    public static MatchEnum from(GlobalMatch match){
        switch (match){
            default:
            case NA:
                return MatchEnum.NA;
            case MATCH:
                return MatchEnum.MATCH;
            case MISMATCH:
                return MatchEnum.MISMATCH;
            case NOMATCH:
                return MatchEnum.NOMATCH;
        }
    }
}
