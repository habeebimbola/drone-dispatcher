package com.musala.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ResponseCode {
    SUCCESS, FAILURE, NOT_FOUND;
}
