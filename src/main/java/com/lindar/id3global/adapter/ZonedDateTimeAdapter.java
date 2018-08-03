package com.lindar.id3global.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

    private static final ZoneId BST_TIME_ZONE = ZoneId.of("Europe/London");

    @Override
    public ZonedDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v).atZone(BST_TIME_ZONE).withZoneSameInstant(ZoneOffset.UTC);
    }

    @Override
    public String marshal(ZonedDateTime v) throws Exception {
        if (v != null) {
            return v.withZoneSameInstant(BST_TIME_ZONE).toLocalDateTime().toString();
        } else {
            return null;
        }
    }
}