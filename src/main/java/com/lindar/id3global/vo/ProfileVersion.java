package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalProfileIDVersion;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileVersion implements SoapInterface<GlobalProfileIDVersion>{
    private String id;
    private long version;

    @Override
    public GlobalProfileIDVersion toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();
        GlobalProfileIDVersion globalProfileIDVersion = factory.createGlobalProfileIDVersion();
        globalProfileIDVersion.setID(id);
        globalProfileIDVersion.setVersion(factory.createGlobalProfileIDVersionVersion(version));
        return globalProfileIDVersion;
    }
}
