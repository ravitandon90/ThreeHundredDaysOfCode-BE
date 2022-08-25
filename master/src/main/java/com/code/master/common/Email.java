package com.code.master.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
public class Email {

    @Schema
    String to;

    @Schema
    String from;

    @Schema
    String subject;

    @Schema
    String text;

    @Schema
    String template;

    @Schema()
    Map<String, Object> properties;
}