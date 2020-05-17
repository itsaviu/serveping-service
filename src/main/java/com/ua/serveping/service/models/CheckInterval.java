package com.ua.serveping.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum  CheckInterval {

    ONE(10 * 1000), FIVE(5 * 60 * 1000);

    private Integer timePeriod;

    public static CheckInterval getCheckIntervalByName(String name) {
        return Stream.of(ONE, FIVE)
                .filter(value -> value.name().equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Invalid Check Interval checkValue %s", name)));
    }
}

