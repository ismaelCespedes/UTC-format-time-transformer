package com.utc.format.transformer.services;

import com.utc.format.transformer.exception.InvalidFormatException;
import com.utc.format.transformer.exception.ParameterRequiredException;
import com.utc.format.transformer.model.TimeRequest;
import com.utc.format.transformer.validation.TimeRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class TimeTransformerValidatorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TimeRequestValidator timeRequestValidator;

    private TimeRequest timeRequest = new TimeRequest();

    private static final String INVALID_TIME = "anyInvalidValue";
    private static final String INVALID_TIME_ZONE = "45";

    private static final String VALID_TIME = "14:05:05";
    private static final String VALID_TIME_ZONE = "+14:5";
    private static final String VALID_TIME_ZONE_HOUR = "+1";

    @Test(expectedExceptions = ParameterRequiredException.class)
    public void testEmptyTimeRequestThenReturnParameterRequiredException() {
       timeRequestValidator.validate(timeRequest);
    }

    @Test(expectedExceptions = InvalidFormatException.class)
    public void testInvalidTimeFormatThenReturnInvalidFormatException() {
        timeRequest.setTime(INVALID_TIME);
        timeRequestValidator.validate(timeRequest);
    }

    @Test(expectedExceptions = InvalidFormatException.class)
    public void testInvalidTimeZoneFormatThenReturnInvalidFormatException() {
        timeRequest.setTimezone(INVALID_TIME_ZONE);
        timeRequest.setTime(VALID_TIME);
        timeRequestValidator.validate(timeRequest);
    }

    @Test()
    public void testValidTimeZoneFormatThenReturnOk() {
        timeRequest.setTimezone(VALID_TIME_ZONE);
        timeRequest.setTime(VALID_TIME);
        timeRequestValidator.validate(timeRequest);
    }

    @Test()
    public void testValidTimezoneHourFormatThenReturnOk() {
        timeRequest.setTimezone(VALID_TIME_ZONE_HOUR);
        timeRequest.setTime(VALID_TIME);
        timeRequestValidator.validate(timeRequest);
    }

}
