package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.ArrayOfstring;
import com.lindar.id3global.internal.vo.GlobalAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Account {
    private String orgID;
    private String orgName;
    private String accountID;
    private String name;
    private String username;
    private String domainName;
    private String email;
    private Date created;
    private Date lastSignInSuccess;
    private Date lastSignInFailed;
    private Boolean active;
    private Boolean locked;
    private Boolean system;
    private List<String> products;
    private Boolean selfCare;
    private Long authenticationCount;
    private Long addressLookUpCount;
    private Boolean passwordNeverExpiresInternal;

    public static Account from(GlobalAccount value){
        Account result = new Account();
        result.setOrgID(value.getOrgID());
        SoapUtils.populate(value.getOrgName(), result::setOrgName);
        result.setAccountID(value.getAccountID());
        SoapUtils.populate(value.getName(), result::setName);
        SoapUtils.populate(value.getUsername(), result::setUsername);
        SoapUtils.populate(value.getDomainName(), result::setDomainName);
        SoapUtils.populate(value.getEmail(), result::setEmail);
        result.setCreated(SoapUtils.mapDate(value.getCreated()));
        result.setLastSignInSuccess(SoapUtils.mapDate(value.getLastSignInSuccess()));
        result.setLastSignInFailed(SoapUtils.mapDate(value.getLastSignInFailed()));
        result.setActive(value.isActive());
        result.setLocked(value.isLocked());
        result.setSystem(value.isSystem());
        SoapUtils.populate(value.getProducts(), ArrayOfstring::getString, result::setProducts);
        result.setSelfCare(value.isSelfCare());
        result.setAuthenticationCount(value.getAuthenticationCount());
        result.setAddressLookUpCount(value.getAddressLookUpCount());
        result.setPasswordNeverExpiresInternal(value.isPasswordNeverExpiresInternal());
        return result;
    }
}
