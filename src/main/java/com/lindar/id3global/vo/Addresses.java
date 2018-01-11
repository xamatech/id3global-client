package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.ArrayOfGlobalAddress;
import com.lindar.id3global.internal.vo.GlobalAddress;
import com.lindar.id3global.internal.vo.GlobalAddresses;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Addresses implements SoapInterface<GlobalAddresses>{

    protected Address currentAddress;
    protected Address previousAddress1;
    protected Address previousAddress2;
    protected Address previousAddress3;
    protected List<Address> historicAddresses;

    @Override
    public GlobalAddresses toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalAddresses addresses = factory.createGlobalAddresses();

        if(currentAddress != null) addresses.setCurrentAddress(factory.createGlobalAddressesCurrentAddress(currentAddress.toSoap()));
        if(previousAddress1 != null) addresses.setPreviousAddress1(factory.createGlobalAddressesPreviousAddress1(previousAddress1.toSoap()));
        if(previousAddress2 != null) addresses.setPreviousAddress2(factory.createGlobalAddressesPreviousAddress2(previousAddress2.toSoap()));
        if(previousAddress3 != null) addresses.setPreviousAddress3(factory.createGlobalAddressesPreviousAddress3(previousAddress3.toSoap()));

        if(historicAddresses != null && !historicAddresses.isEmpty()) {
            ArrayOfGlobalAddress arrayOfGlobalAddress = new ArrayOfGlobalAddress();
            arrayOfGlobalAddress.getGlobalAddress().addAll(toSoap(historicAddresses));
            addresses.setHistoricAddresses(factory.createGlobalAddressesHistoricAddresses(arrayOfGlobalAddress));
        }

        return addresses;
    }

    private List<GlobalAddress> toSoap(List<Address> addresses){
        return addresses.stream().map(Address::toSoap).collect(Collectors.toList());
    }

    public static Addresses from(GlobalAddresses value) {
        Addresses result = new Addresses();

        if(value.getCurrentAddress() != null) result.setCurrentAddress(Address.from(value.getCurrentAddress().getValue()));
        if(value.getPreviousAddress1() != null) result.setPreviousAddress1(Address.from(value.getPreviousAddress1().getValue()));
        if(value.getPreviousAddress2() != null) result.setPreviousAddress2(Address.from(value.getPreviousAddress2().getValue()));
        if(value.getPreviousAddress3() != null) result.setPreviousAddress3(Address.from(value.getPreviousAddress3().getValue()));

        if(value.getHistoricAddresses() != null && !value.getHistoricAddresses().getValue().getGlobalAddress().isEmpty()){
            result.setHistoricAddresses(value.getHistoricAddresses().getValue().getGlobalAddress().stream().map(Address::from).collect(Collectors.toList()));
        }

        return result;
    }

}
