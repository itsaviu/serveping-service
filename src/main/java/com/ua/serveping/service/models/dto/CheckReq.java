package com.ua.serveping.service.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ua.serveping.service.jsonmappers.CheckIntervalDeserializer;
import com.ua.serveping.service.jsonmappers.CheckValueDeserializer;
import com.ua.serveping.service.models.CheckInterval;
import com.ua.serveping.service.models.CheckValue;
import lombok.Data;

@Data
public class CheckReq {

    @JsonDeserialize(using = CheckValueDeserializer.class)
    private CheckValue checkValue;

    private String name;

    private String endpoint;

    @JsonDeserialize(using = CheckIntervalDeserializer.class)
    private CheckInterval checkInterval;
}
