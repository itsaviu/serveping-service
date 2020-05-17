package com.ua.serveping.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum CheckValue {

    HTTP_HTTPS("HTTP_HTTPS"), PING("PING");

    private String name;

    public static CheckValue getCheckValueByName(String name) {
        return Stream.of(HTTP_HTTPS, PING)
                .filter(value -> value.name.equals(name))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Cannot find %s in check value", name)));
    }
}
