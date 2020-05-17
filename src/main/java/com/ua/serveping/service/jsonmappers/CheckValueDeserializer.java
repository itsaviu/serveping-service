package com.ua.serveping.service.jsonmappers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ua.serveping.service.models.CheckValue;

import java.io.IOException;

public class CheckValueDeserializer extends JsonDeserializer<CheckValue> {
    @Override
    public CheckValue deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String name = parser.getText().trim();
        return CheckValue.getCheckValueByName(name);
    }
}
