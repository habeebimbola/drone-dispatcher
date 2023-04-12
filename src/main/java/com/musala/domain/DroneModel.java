package com.musala.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum DroneModel {LightWeight, MiddleWeight, CruiserWeight, HeavyWeight}
