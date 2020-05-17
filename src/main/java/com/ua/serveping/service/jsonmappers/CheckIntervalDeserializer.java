package com.ua.serveping.service.jsonmappers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ua.serveping.service.models.CheckInterval;

import java.io.IOException;

public class CheckIntervalDeserializer extends JsonDeserializer<CheckInterval> {

    @Override
    public CheckInterval deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String name = parser.getText().trim();
        return CheckInterval.getCheckIntervalByName(name);
    }
}
