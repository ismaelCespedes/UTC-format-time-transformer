package com.utc.format.transformer.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.utc.format.transformer.model.Response;
import com.utc.format.transformer.model.TimeRequest;
import com.utc.format.transformer.validation.TimeRequestValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeTransformerServiceImpl implements TimeTransformerService {

    private static final String UTC_TIMEZONE = "UTC";
    private static final String DEFAULT_FORMAT = "HH:mm:ss";
    private static final String DEFAULT_FILE_NAME = "response.json";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
    protected static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    static
    {
        mapper.registerSubtypes(Response.class);
    }

    private final TimeRequestValidator timeRequestValidator;

    public TimeTransformerServiceImpl(TimeRequestValidator timeRequestValidator) {
        this.timeRequestValidator = timeRequestValidator;
    }

    @Override
    public byte[] getChangedTimezoneJsonFile(TimeRequest timeRequest) throws Exception {
        TimeRequest timeResponse = changeTimezone(timeRequest);
        String timeResponseJson = mapper.writeValueAsString(new Response(timeResponse));

        File newFile = new File(DEFAULT_FILE_NAME);
        newFile.createNewFile();

        try(FileOutputStream fileOutputStream = new FileOutputStream(newFile)){
            byte[] strToBytes = timeResponseJson.getBytes();
            fileOutputStream.write(strToBytes);

            return IOUtils.toByteArray(new FileInputStream(newFile));
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public TimeRequest changeTimezone(TimeRequest timeRequest) throws Exception {

        timeRequestValidator.validate(timeRequest);

        LocalTime result = LocalTime.from(dateTimeFormatter.parse(timeRequest.getTime()));

        String timeByTimezone = setDateByTimezone(result, timeRequest.getTimezone());

        TimeRequest response = new TimeRequest();
        response.setTimezone(UTC_TIMEZONE);
        response.setTime(timeByTimezone);

        return response;
    }

    private String setDateByTimezone (LocalTime localTime, String timezone) {
        int minutesAdjustment = 0;
        String[] parts = timezone.split(":");
        int hoursAdjustment =  Integer.parseInt(parts[0].substring(1));
        if (parts.length==2) {
            minutesAdjustment = Integer.parseInt(parts[1]);
        }

        if (timezone.startsWith("-")) {
            localTime = localTime.minusHours(hoursAdjustment);
            localTime = localTime.minusMinutes(minutesAdjustment);
        } else {
            localTime = localTime.plusHours(hoursAdjustment);
            localTime = localTime.plusMinutes(minutesAdjustment);
        }
        return localTime.format(dateTimeFormatter);
    }

}
