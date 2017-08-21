package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfGlobalItemCheckResultCode;
import com.lindar.id3global.internal.vo.GlobalItemCheckResultCodes;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemCheckResultCodes {

    protected String name;
    protected String description;
    protected List<ItemCheckResultCode> comment = Collections.emptyList();
    protected List<ItemCheckResultCode> match = Collections.emptyList();
    protected List<ItemCheckResultCode> warning = Collections.emptyList();
    protected List<ItemCheckResultCode> mismatch = Collections.emptyList();
    protected Integer id;
    protected MatchEnum pass;
    protected MatchEnum address;
    protected MatchEnum forename;
    protected MatchEnum surname;
    protected MatchEnum dob;
    protected MatchEnum alert;
    protected String country;


    public static ItemCheckResultCodes from (GlobalItemCheckResultCodes from){

        ItemCheckResultCodes resultCodes = new ItemCheckResultCodes();

        if(from.getName() != null) resultCodes.setName(from.getName().getValue());
        if(from.getDescription() != null) resultCodes.setDescription(from.getDescription().getValue());

        if(from.getComment() != null) resultCodes.setComment(transformToItemList(from.getComment().getValue()));
        if(from.getMatch() != null) resultCodes.setMatch(transformToItemList(from.getMatch().getValue()));
        if(from.getWarning() != null) resultCodes.setComment(transformToItemList(from.getWarning().getValue()));
        if(from.getMismatch() != null) resultCodes.setMismatch(transformToItemList(from.getMismatch().getValue()));

        resultCodes.setId(from.getID());
        resultCodes.setPass(MatchEnum.from(from.getPass()));
        resultCodes.setAddress(MatchEnum.from(from.getAddress()));
        resultCodes.setForename(MatchEnum.from(from.getForename()));
        resultCodes.setSurname(MatchEnum.from(from.getSurname()));
        resultCodes.setDob(MatchEnum.from(from.getDOB()));
        resultCodes.setAlert(MatchEnum.from(from.getAlert()));

        if(from.getCountry() != null) resultCodes.setCountry(from.getCountry().getValue());
        return resultCodes;
    }

    private static List<ItemCheckResultCode> transformToItemList(ArrayOfGlobalItemCheckResultCode from){
        return from.getGlobalItemCheckResultCode().stream().map(ItemCheckResultCode::from).collect(Collectors.toList());
    }
}
