package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
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
        kangaroos.put(1, new Kangaroo(1, "mimi", 1.8, 90, "male", false ));
    }

    @GetMapping("")
    public List<Kangaroo> getAllKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id){
        //TODO [ezgi] check id is not negative
        //TODO [ezgi] check if id exist in map
        return  kangaroos.get(id);
    }

    @PostMapping("")
    public void addKangaroo(@RequestBody Kangaroo kangaroo){
        kangaroos.put(kangaroo.getId(), kangaroo);
    }

    @PutMapping("/{id}")
    public void updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        kangaroos.replace(kangaroo.getId(), kangaroo);
    }

    @DeleteMapping("/{id}")
    public void deleteKangaroo(@PathVariable int id){
        kangaroos.remove(id);
    }

}
