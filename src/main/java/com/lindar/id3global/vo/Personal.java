package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfGlobalAlternateName;
import com.lindar.id3global.internal.vo.GlobalPersonal;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Personal implements SoapInterface<GlobalPersonal> {

    private PersonalDetails personalDetails;
    private AlternateName alternateName;
    private List<AlternateName> aliases;

    @Override
    public GlobalPersonal toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalPersonal result = factory.createGlobalPersonal();

        if(personalDetails != null) result.setPersonalDetails(factory.createGlobalPersonalPersonalDetails(personalDetails.toSoap()));
        if(alternateName != null) result.setAlternateName(factory.createGlobalPersonalAlternateName(alternateName.toSoap()));
        if(aliases != null) result.setAliases(factory.createGlobalPersonalAliases(toSoap(aliases)));

        return result;
    }

    public static Personal from(GlobalPersonal value){
        Personal result = new Personal();
        if(value.getPersonalDetails() != null) result.setPersonalDetails(PersonalDetails.from(value.getPersonalDetails().getValue()));
        if(value.getAlternateName() != null) result.setAlternateName(AlternateName.from(value.getAlternateName().getValue()));

        if(value.getAliases() != null && !value.getAliases().getValue().getGlobalAlternateName().isEmpty()){
            result.setAliases(value.getAliases().getValue().getGlobalAlternateName().stream().map(AlternateName::from).collect(Collectors.toList()));
        }
        return result;
    }

    private ArrayOfGlobalAlternateName toSoap(List<AlternateName> value){
        ArrayOfGlobalAlternateName array = new ArrayOfGlobalAlternateName();
        array.getGlobalAlternateName().addAll(value.stream().map(AlternateName::toSoap).collect(Collectors.toList()));
        return array;
    }
}
