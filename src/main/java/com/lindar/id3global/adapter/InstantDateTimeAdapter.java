package com.lindar.id3global.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.*;

public class InstantDateTimeAdapter extends XmlAdapter<String, Instant> {

    private static final ZoneId BST_TIME_ZONE = ZoneId.of("Europe/London");

    @Override
    public Instant unmarshal(String v) {
        // attempt to parse with a timezone as some of the dates include the zone information
        try {
            return ZonedDateTime.parse(v).withZoneSameInstant(ZoneOffset.UTC).toInstant();
        } catch (Exception e) {
            // if that fails assume the date is in the timezone Europe/London
            return LocalDateTime.parse(v).atZone(BST_TIME_ZONE).withZoneSameInstant(ZoneOffset.UTC).toInstant();
        }
    }

    @Override
    public String marshal(Instant v) {
        if (v != null) {
            return v.atZone(BST_TIME_ZONE).toLocalDateTime().toString();
        } else {
            return null;
        }
    }
}