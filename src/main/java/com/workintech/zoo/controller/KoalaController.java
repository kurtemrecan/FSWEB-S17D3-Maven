package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAllKoalas(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.BAD_REQUEST);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala){ //göderilen JSON nesnesini Koala nesnesine çevirdik
        if(koala == null){
            throw new ZooException("Koala object cannot be null.", HttpStatus.BAD_REQUEST);
        }
        if(koala.getId()<=0){
            throw new ZooException("Id must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala){
        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.BAD_REQUEST);
        }
        koalas.put(id,koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.BAD_REQUEST);
        }
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }


}
