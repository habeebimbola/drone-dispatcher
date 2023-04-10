package com.musala.domain;

import jakarta.persistence.*;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Medication")
@Table(name = "MEDICATION")
public class Medication implements Serializable {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)

    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "CODE")
    private String code;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DroneMedication> droneMedications = new ArrayList<>();

    @Transient
    private InputStream imageStream;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public List<DroneMedication> getDroneMedications() {
        return droneMedications;
    }

    public void setDroneMedications(List<DroneMedication> droneMedications) {
        this.droneMedications = droneMedications;
    }
}