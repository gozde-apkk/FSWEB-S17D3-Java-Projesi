package com.workintech.zoo.ZooAplication.entity;


import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class Koala {
private int id;
private String name;
private int weight;
private int sleepHour;
private Gender gender;


    public Koala(int id, String name, int weight, int sleepHour, Gender gender) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sleepHour = sleepHour;
        this.gender = gender;

    }
}
