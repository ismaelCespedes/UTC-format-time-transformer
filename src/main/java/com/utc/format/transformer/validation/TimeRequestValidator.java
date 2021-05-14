package com.utc.format.transformer.validation;

import com.utc.format.transformer.exception.ParameterRequiredException;
import com.utc.format.transformer.exception.InvalidFormatException;
import com.utc.format.transformer.model.TimeRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TimeRequestValidator {

    public void validate(TimeRequest timeRequest) {
        //time validation
        if (!StringUtils.hasLength(timeRequest.getTime())) {
            throw new ParameterRequiredException("Time attribute is required.");
        }
        String[] timePats = timeRequest.getTime().split(":");
        if (timePats.length!=3) {
            throw new InvalidFormatException("Time attribute has an invalid format, please use HH:mm:ss");
        }

        try {
            Integer.parseInt(timePats[0]);
            Integer.parseInt(timePats[1]);
            Integer.parseInt(timePats[2]);
        }catch (Exception e) {
            throw new InvalidFormatException("Time attribute has an invalid value, please use HH:mm:ss");
        }

        //timezone validation
        if (!StringUtils.hasLength(timeRequest.getTimezone())) {
            throw new ParameterRequiredException("Time attribute is required.");
        }
        if (!(timeRequest.getTimezone().startsWith("+") || timeRequest.getTimezone().startsWith("-") || timeRequest.getTimezone().startsWith("±"))) {
            throw new InvalidFormatException("Timezone attribute should be start with +, - or ± sign.");
        }

        try{
            String[] timezoneParts = timeRequest.getTimezone().split(":");
            Integer.parseInt(timezoneParts[0].substring(1));
            if (timezoneParts.length==2) {
                Integer.parseInt(timezoneParts[1]);
            } else if (timezoneParts.length>2) {
                throw new InvalidFormatException("Timezone attribute has an invalid format, please use one of the followings: HH:mm, H:m, H");
            }
        }catch (Exception e) {
            throw new InvalidFormatException("Timezone attribute has an invalid value, please use one of the followings formats: HH:mm, H:m, H");
        }
    }
}
