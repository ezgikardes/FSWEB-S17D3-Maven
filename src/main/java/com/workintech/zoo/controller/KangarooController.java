package com.workintech.zoo.controller;

import com.workintech.zoo.dto.KangarooResponse;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if(id <= 0){
            throw new ZooException("Id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)){
            throw new ZooException("Given id doesn't exist", HttpStatus.NOT_FOUND);
        }
        return  kangaroos.get(id);
    }

    @PostMapping("")
    public ResponseEntity<Kangaroo> addKangaroo(@RequestBody Kangaroo kangaroo){
        //null check
        if(kangaroo.getId() <= 0
                || kangaroo.getName() == null
                || kangaroo.getHeight() <= 0
                || kangaroo.getWeight() <= 0
                || kangaroo.getIsAggressive() == null
                || kangaroo.getGender() == null ){
            throw new ZooException("Invalid kangaroo data", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        kangaroos.replace(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KangarooResponse> deleteKangaroo(@PathVariable int id){
        //optional if controls:
        if(id <= 0){
            throw new ZooException("Id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Given id doesn't exist", HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return new ResponseEntity<>(new KangarooResponse(id, "is deleted"), HttpStatus.OK);
    }

}
