package com.lindar.id3global.vo.match;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.ArrayOfstring;
import com.lindar.id3global.internal.vo.GlobalSanctionsMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class SanctionsMatch {
    private String sanctionID;
    private String url;
    private Long score;
    private String caseID;
    private String searchID;
    private Boolean fcraEnabled;
    private List<String> tiers;

    public static SanctionsMatch from(GlobalSanctionsMatch value){
        SanctionsMatch result = new SanctionsMatch();

        SoapUtils.populate(value.getSanctionID(), result::setSanctionID);
        SoapUtils.populate(value.getUrl(), result::setUrl);
        SoapUtils.populate(value.getScore(), result::setScore);
        SoapUtils.populate(value.getCaseID(), result::setCaseID);
        SoapUtils.populate(value.getSearchID(), result::setSearchID);
        SoapUtils.populate(value.getTiers(), ArrayOfstring::getString, result::setTiers);
        result.setFcraEnabled(value.isFCRAEnabled());

        return result;
    }
}
