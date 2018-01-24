package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PEPIntelligenceData {

    private String fullname;
    private List<String> aliases;
    private List<SanctionsAddress> sanctionsAddresses;
    private List<SanctionsDate> sanctionsDates;
    private List<String> identityInformation;
    private List<SanctionsPosition> sanctionsPositions;
    private Integer tier;

    public static PEPIntelligenceData from(GlobalPEPIntelligenceData value){
        PEPIntelligenceData result = new PEPIntelligenceData();

        SoapUtils.populate(value.getFullname(), result::setFullname);
        SoapUtils.populate(value.getAliases(), ArrayOfstring::getString, result::setAliases);
        SoapUtils.populate(value.getSanctionsAddresses(), PEPIntelligenceData::transformSanctionsAddressList, result::setSanctionsAddresses);
        SoapUtils.populate(value.getSanctionsDates(), PEPIntelligenceData::transformSanctionsDateList, result::setSanctionsDates);
        SoapUtils.populate(value.getSanctionsPositions(), PEPIntelligenceData::transformSanctionsPositionList, result::setSanctionsPositions);

        return result;
    }

    private static List<SanctionsAddress> transformSanctionsAddressList(ArrayOfGlobalSanctionsAddress from){
        return from.getGlobalSanctionsAddress().stream().map(SanctionsAddress::from).collect(Collectors.toList());
    }

    private static List<SanctionsDate> transformSanctionsDateList(ArrayOfGlobalSanctionsDate from){
        return from.getGlobalSanctionsDate().stream().map(SanctionsDate::from).collect(Collectors.toList());
    }

    private static List<SanctionsPosition> transformSanctionsPositionList(ArrayOfGlobalSanctionsPosition from){
        return from.getGlobalSanctionsPosition().stream().map(SanctionsPosition::from).collect(Collectors.toList());
    }
}
