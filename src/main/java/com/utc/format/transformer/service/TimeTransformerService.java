package com.utc.format.transformer.service;

import com.utc.format.transformer.model.TimeRequest;

public interface TimeTransformerService {
    byte[] getChangedTimezoneJsonFile(TimeRequest timeRequest) throws Exception;

    TimeRequest changeTimezone(TimeRequest timeRequest) throws Exception;
}
