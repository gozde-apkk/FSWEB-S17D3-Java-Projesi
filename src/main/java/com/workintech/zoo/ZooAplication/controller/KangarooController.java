package com.workintech.zoo.ZooAplication.controller;


import com.workintech.zoo.ZooAplication.entity.Gender;
import com.workintech.zoo.ZooAplication.entity.Kangaroo;
import com.workintech.zoo.ZooAplication.exceptions.AnimalValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
        kangaroos.put(1, new Kangaroo(1,"cUTE",30.00, Gender.FEMALE,30,false));

    }

    /**
     * [GET]/workintech/v1/kangaroos/ => getALL
     * [GET]/workintech/v1/kangaroos/id => get
     * [POST]/workintech/v1/kangaroos/ => save
     * [PUT]/workintech/v1/kangaroos/id => update
     * [DELETE]/workintech/v1/kangaroos/id => delete
     *
     * [GET]/workintech/v1.2.0/kangaroos/1/details => Get kangaroos detail
     * [GET]/workintech/v1/kangaroos/1/details/1 => kangarooId, kangarooIdDetail
     *
     */

    @GetMapping("/")
    public List<Kangaroo> getAll(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo get(@PathVariable int id){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        return kangaroos.get(id);
    }

    @PostMapping("/")
    public Kangaroo save(@RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdValid(kangaroo.getId());
        AnimalValidator.isIdExist(kangaroos, kangaroo.getId());
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);

        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }

}