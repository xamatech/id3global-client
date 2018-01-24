package com.lindar.id3global.vo;

import com.lindar.id3global.SoapUtils;
import com.lindar.id3global.internal.vo.ArrayOfGlobalItemCheckResultCode;
import com.lindar.id3global.internal.vo.GlobalItemCheckResultCodes;
import com.lindar.id3global.internal.vo.GlobalSanctionsResultCodes;
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


    public static ItemCheckResultCodes from (GlobalItemCheckResultCodes value){

        // process specific result codes
        if(value instanceof GlobalSanctionsResultCodes){
            return SanctionsResultCodes.from((GlobalSanctionsResultCodes)value);
        }

        // no specific result code found, procsss as default
        ItemCheckResultCodes result = new ItemCheckResultCodes();
        populate(value, result);
        return result;
    }

    protected static void populate (GlobalItemCheckResultCodes value, ItemCheckResultCodes result){
        SoapUtils.populate(value.getName(), result::setName);
        SoapUtils.populate(value.getDescription(), result::setDescription);
        SoapUtils.populate(value.getComment(), ItemCheckResultCodes::transformToList, result::setComment);
        SoapUtils.populate(value.getMatch(), ItemCheckResultCodes::transformToList, result::setMatch);
        SoapUtils.populate(value.getWarning(), ItemCheckResultCodes::transformToList, result::setWarning);
        SoapUtils.populate(value.getMismatch(), ItemCheckResultCodes::transformToList, result::setMismatch);
        result.setId(value.getID());
        SoapUtils.populate(value.getPass(), MatchEnum::from,result::setPass);
        SoapUtils.populate(value.getAddress(), MatchEnum::from,result::setAddress);
        SoapUtils.populate(value.getForename(), MatchEnum::from,result::setForename);
        SoapUtils.populate(value.getSurname(), MatchEnum::from, result::setSurname);
        SoapUtils.populate(value.getDOB(), MatchEnum::from, result::setDob);
        SoapUtils.populate(value.getAlert(), MatchEnum::from, result::setAlert);
        SoapUtils.populate(value.getCountry(), result::setCountry);
    }

    protected static List<ItemCheckResultCode> transformToList(ArrayOfGlobalItemCheckResultCode from){
        return from.getGlobalItemCheckResultCode().stream().map(ItemCheckResultCode::from).collect(Collectors.toList());
    }
}
