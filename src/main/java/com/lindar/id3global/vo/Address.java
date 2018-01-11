package com.lindar.id3global.vo;

import com.lindar.id3global.internal.vo.GlobalAddress;
import com.lindar.id3global.internal.vo.ObjectFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address implements SoapInterface<GlobalAddress>{
    private String country;
    private String street;
    private String subStreet;
    private String city;
    private String subCity;
    private String stateDistrict;
    private String poBox;
    private String region;
    private String principality;
    private String zipPostcode;
    private String dpsZipPlus;
    private String cedexMailsort;
    private String department;
    private String company;
    private String building;
    private String subBuilding;
    private String premise;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String addressLine6;
    private String addressLine7;
    private String addressLine8;
    private Integer firstYearOfResidence;
    private Integer lastYearOfResidence;
    private Integer firstMonthOfResidence;
    private Integer lastMonthOfResidence;

    @Override
    public GlobalAddress toSoap() {
        ObjectFactory factory = ObjectFactory.getInstance();

        GlobalAddress address = factory.createGlobalAddress();

        if (StringUtils.isNotBlank(country)) address.setCountry(factory.createGlobalAddressCountry(country));
        if (StringUtils.isNotBlank(street)) address.setStreet(factory.createGlobalAddressStreet(street));
        if (StringUtils.isNotBlank(subStreet)) address.setSubStreet(factory.createGlobalAddressSubStreet(subStreet));
        if (StringUtils.isNotBlank(city)) address.setCity(factory.createGlobalAddressCity(city));
        if (StringUtils.isNotBlank(subCity)) address.setSubCity(factory.createGlobalAddressSubCity(subCity));
        if (StringUtils.isNotBlank(stateDistrict)) address.setStateDistrict(factory.createGlobalAddressStateDistrict(stateDistrict));
        if (StringUtils.isNotBlank(poBox)) address.setPOBox(factory.createGlobalAddressPOBox(poBox));
        if (StringUtils.isNotBlank(region)) address.setRegion(factory.createGlobalAddressRegion(region));
        if (StringUtils.isNotBlank(principality)) address.setPrincipality(factory.createGlobalAddressPrincipality(principality));
        if (StringUtils.isNotBlank(zipPostcode)) address.setZipPostcode(factory.createGlobalAddressZipPostcode(zipPostcode));
        if (StringUtils.isNotBlank(dpsZipPlus)) address.setDpsZipPlus(factory.createGlobalAddressDpsZipPlus(dpsZipPlus));
        if (StringUtils.isNotBlank(cedexMailsort)) address.setCedexMailsort(factory.createGlobalAddressCedexMailsort(cedexMailsort));
        if (StringUtils.isNotBlank(department)) address.setDepartment(factory.createGlobalAddressDepartment(department));
        if (StringUtils.isNotBlank(company)) address.setCompany(factory.createGlobalAddressCompany(company));
        if (StringUtils.isNotBlank(building)) address.setBuilding(factory.createGlobalAddressBuilding(building));
        if (StringUtils.isNotBlank(subBuilding)) address.setSubBuilding(factory.createGlobalAddressSubBuilding(subBuilding));
        if (StringUtils.isNotBlank(premise)) address.setPremise(factory.createGlobalAddressPremise(premise));
        if (StringUtils.isNotBlank(addressLine1)) address.setAddressLine1(factory.createGlobalAddressAddressLine1(addressLine1));
        if (StringUtils.isNotBlank(addressLine2)) address.setAddressLine2(factory.createGlobalAddressAddressLine2(addressLine2));
        if (StringUtils.isNotBlank(addressLine3)) address.setAddressLine3(factory.createGlobalAddressAddressLine3(addressLine3));
        if (StringUtils.isNotBlank(addressLine4)) address.setAddressLine4(factory.createGlobalAddressAddressLine4(addressLine4));
        if (StringUtils.isNotBlank(addressLine5)) address.setAddressLine5(factory.createGlobalAddressAddressLine5(addressLine5));
        if (StringUtils.isNotBlank(addressLine6)) address.setAddressLine6(factory.createGlobalAddressAddressLine6(addressLine6));
        if (StringUtils.isNotBlank(addressLine7)) address.setAddressLine7(factory.createGlobalAddressAddressLine7(addressLine7));
        if (StringUtils.isNotBlank(addressLine8)) address.setAddressLine8(factory.createGlobalAddressAddressLine8(addressLine8));
        if (firstYearOfResidence != null) address.setFirstYearOfResidence(factory.createGlobalAddressFirstYearOfResidence(firstYearOfResidence));
        if (lastYearOfResidence != null) address.setLastYearOfResidence(factory.createGlobalAddressLastYearOfResidence(lastYearOfResidence));
        if (firstMonthOfResidence != null) address.setFirstMonthOfResidence(factory.createGlobalAddressFirstMonthOfResidence(firstMonthOfResidence));
        if (lastMonthOfResidence != null) address.setLastMonthOfResidence(factory.createGlobalAddressLastMonthOfResidence(lastMonthOfResidence));

        return address;
    }

    public static Address from(GlobalAddress value) {
        Address result = new Address();

        if (value.getCountry() != null) result.setCountry(value.getCountry().getValue());
        if (value.getStreet() != null) result.setStreet(value.getStreet().getValue());
        if (value.getSubStreet() != null) result.setSubStreet(value.getSubStreet().getValue());
        if (value.getCity() != null) result.setCity(value.getCity().getValue());
        if (value.getSubCity() != null) result.setSubCity(value.getSubCity().getValue());
        if (value.getStateDistrict() != null) result.setStateDistrict(value.getStateDistrict().getValue());
        if (value.getPOBox() != null) result.setPoBox(value.getPOBox().getValue());
        if (value.getRegion() != null) result.setRegion(value.getRegion().getValue());
        if (value.getPrincipality() != null) result.setPrincipality(value.getPrincipality().getValue());
        if (value.getZipPostcode() != null) result.setZipPostcode(value.getZipPostcode().getValue());
        if (value.getDpsZipPlus() != null) result.setDpsZipPlus(value.getDpsZipPlus().getValue());
        if (value.getCedexMailsort() != null) result.setCedexMailsort(value.getCedexMailsort().getValue());
        if (value.getDepartment() != null) result.setDepartment(value.getDepartment().getValue());
        if (value.getCompany() != null) result.setCompany(value.getCompany().getValue());
        if (value.getBuilding() != null) result.setBuilding(value.getBuilding().getValue());
        if (value.getSubBuilding() != null) result.setSubBuilding(value.getSubBuilding().getValue());
        if (value.getPremise() != null) result.setPremise(value.getPremise().getValue());
        if (value.getAddressLine1() != null) result.setAddressLine1(value.getAddressLine1().getValue());
        if (value.getAddressLine2() != null) result.setAddressLine2(value.getAddressLine2().getValue());
        if (value.getAddressLine3() != null) result.setAddressLine3(value.getAddressLine3().getValue());
        if (value.getAddressLine4() != null) result.setAddressLine4(value.getAddressLine4().getValue());
        if (value.getAddressLine5() != null) result.setAddressLine5(value.getAddressLine5().getValue());
        if (value.getAddressLine6() != null) result.setAddressLine6(value.getAddressLine6().getValue());
        if (value.getAddressLine7() != null) result.setAddressLine7(value.getAddressLine7().getValue());
        if (value.getAddressLine8() != null) result.setAddressLine8(value.getAddressLine8().getValue());
        if (value.getFirstYearOfResidence() != null) result.setFirstYearOfResidence(value.getFirstYearOfResidence().getValue());
        if (value.getLastYearOfResidence() != null) result.setLastYearOfResidence(value.getLastYearOfResidence().getValue());
        if (value.getFirstMonthOfResidence() != null) result.setFirstMonthOfResidence(value.getFirstMonthOfResidence().getValue());
        if (value.getLastMonthOfResidence() != null) result.setLastMonthOfResidence(value.getLastMonthOfResidence().getValue());

        return result;
    }


}
