package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.ArrayOfGlobalAuthentication;
import com.lindar.id3global.internal.vo.GlobalAuthentications;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Authentications {
    private List<AuthenticationSummary> authentications;
    private long pageSize;
    private long totalPages;
    private long totalAuthentications;

    public static Authentications from(GlobalAuthentications value){
        Authentications result = new Authentications();
        SoapUtils.populate(value.getAuthentications(), Authentications::transformAuthenticationList, result::setAuthentications);
        SoapUtils.populate(value::getPageSize, result::setPageSize);
        SoapUtils.populate(value::getTotalPages, result::setTotalPages);
        SoapUtils.populate(value::getTotalAuthentications, result::setTotalAuthentications);
        return result;
    }

    private static List<AuthenticationSummary> transformAuthenticationList(ArrayOfGlobalAuthentication from){
        return from.getGlobalAuthentication().stream().map(AuthenticationSummary::from).collect(Collectors.toList());
    }
}
