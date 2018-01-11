package com.lindar.id3global.vo;

import lombok.Data;

@Data
public class AccessCredentials {
    private String apiUrl;
    private String profileId;
    private long profileVersion;
    private String orgId;

    private String username;
    private String password;
}