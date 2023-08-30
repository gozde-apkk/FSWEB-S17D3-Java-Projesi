package com.workintech.zoo.ZooAplication.controller;

import com.workintech.zoo.ZooAplication.entity.Gender;
import com.workintech.zoo.ZooAplication.entity.Koala;
import com.workintech.zoo.ZooAplication.exceptions.AnimalValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
        koalas.put(1, new Koala(1, "George", 4, Gender.MALE, 20));
    }

    @GetMapping("/{id}")
    public Koala get(@PathVariable int id){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(koalas, id);
        return koalas.get(id);
    }

}