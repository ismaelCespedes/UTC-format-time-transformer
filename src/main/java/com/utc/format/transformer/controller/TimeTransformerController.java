package com.utc.format.transformer.controller;

import com.utc.format.transformer.model.TimeRequest;
import com.utc.format.transformer.service.TimeTransformerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TimeTransformerController {

    private final TimeTransformerService timeTransformerService;

    public TimeTransformerController(TimeTransformerService timeTransformerService) {
        this.timeTransformerService = timeTransformerService;
    }

    @PostMapping(
            value = "/transform-time",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] transformType(@RequestBody TimeRequest timeRequest) throws Exception {
        return timeTransformerService.getChangedTimezoneJsonFile(timeRequest);
    }
}
