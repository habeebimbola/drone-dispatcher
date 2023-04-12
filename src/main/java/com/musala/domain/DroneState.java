package com.musala.domain;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum DroneState {
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED;

}
