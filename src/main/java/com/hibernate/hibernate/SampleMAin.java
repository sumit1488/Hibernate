package com.hibernate.hibernate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class SampleMain {
public static void main(String[] args) {
	Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
	allZoneIds.stream().forEach(x -> System.out.println(x));
	System.out.println("===== "+ ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Australia/Sydney")));
}
}
