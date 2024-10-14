package com.workintech.zoo.controller;

import com.workintech.zoo.dto.KoalaResponse;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        koalas.put(1, new Koala(1, "poncik", 15, 3, "female"));
    }

    @GetMapping("")
    public List<Koala> getAllKoalas(){
       return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)){
            throw new ZooException("Given id doesn't exist", HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping("")
    public Koala addKoala(@RequestBody Koala koala){
        //null check
        if(koala.getId() <= 0 || koala.getName() == null
        || koala.getWeight() <= 0 || koala.getSleepHour() <= 0
        || koala.getGender() == null){
            throw new ZooException("Invalid koala data", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala){
        koalas.replace(koala.getId(), koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KoalaResponse> deleteKoala(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Id doesn't exist", HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
        return new ResponseEntity<>(new KoalaResponse(id, "is deleted"), HttpStatus.OK);
    }
}
