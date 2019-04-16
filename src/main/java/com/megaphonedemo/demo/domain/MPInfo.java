package com.megaphonedemo.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors
public class MPInfo {
    private Collection<String> urls;
    private HeaderInfo headers;

    @Data
    @Accessors
    public static final class HeaderInfo {
        @JsonProperty("X-Megaphone-Payload")
        private String headerPayloadOne;

        @JsonProperty("X-Megaphone-Payload-2")
        private String headerPayloadTwo;
    }
}
