package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.ArrayOfGlobalSanctionsMatch;
import com.lindar.id3global.internal.vo.GlobalSanctionsResultCodes;
import com.lindar.id3global.vo.match.SanctionsMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SanctionsResultCodes extends ItemCheckResultCodes {

    private List<SanctionsMatch> sanctionsMatches;

    public static ItemCheckResultCodes from (GlobalSanctionsResultCodes value){
        SanctionsResultCodes result = new SanctionsResultCodes();

        populate(value, result);

        SoapUtils.populate(value.getSanctionsMatches(), SanctionsResultCodes::transformToList, result::setSanctionsMatches);

        return result;
    }

    protected static List<SanctionsMatch> transformToList(ArrayOfGlobalSanctionsMatch from){
        return from.getGlobalSanctionsMatch().stream().map(SanctionsMatch::from).collect(Collectors.toList());
    }

}
