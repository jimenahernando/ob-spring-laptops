package com.jimenahernando.entrega_1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private LocalDate dateProduction;

    public Laptop() {
    }

    public Laptop(Long id, String brand, String model, LocalDate dateProduction) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dateProduction = dateProduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(LocalDate dateProduction) {
        this.dateProduction = dateProduction;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dateProduction=" + dateProduction +
                '}';
    }
}