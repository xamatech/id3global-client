package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalItemCheckResultCode;
import lombok.Data;

@Data
public class ItemCheckResultCode {

    private String description;
    private Integer code;
    private String override;

    public static ItemCheckResultCode from (GlobalItemCheckResultCode from){
        ItemCheckResultCode resultCode = new ItemCheckResultCode();
        if(from.getDescription() != null) resultCode.setDescription(from.getDescription().getValue());
        if(from.getCode() != null) resultCode.setCode(from.getCode());
        if(from.getOverride() != null) resultCode.setOverride(from.getOverride().getValue());

        return resultCode;
    }
}
