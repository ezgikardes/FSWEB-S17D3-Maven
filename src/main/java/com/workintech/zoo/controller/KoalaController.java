package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
        koalas.put(1, new Koala(1, "poncik", 3, 15, "female"));
    }

    @GetMapping("")
    public List<Koala> getAllKoalas(){
       return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id){
        //TODO [ezgi] check id is not negative
        //TODO [ezgi] check if id exist in map
        return koalas.get(id);
    }

    @PostMapping("")
    public void addKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
    }

    @PutMapping("/{id}")
    public void updateKoala(@PathVariable int id, @RequestBody Koala koala){
        koalas.replace(koala.getId(), koala);
    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable int id){
        koalas.remove(id);
    }
}
