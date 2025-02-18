package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Bu sınıfın bir REST API denetleyicisi olduğunu belirtir.
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;


    @PostConstruct //Bean oluşturulduğun çalışacak metot
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping()
    public List<Kangaroo> getAllKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id){

        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){ //göderilen JSON nesnesini Kangaroo nesnesine çevirdik
        if(kangaroo.getId() <= 0 ){
            throw new ZooException("ID must be greater than zero.", HttpStatus.BAD_REQUEST);
        }
        if(kangaroo == null){
            throw new ZooException("Kangaroo object cannot be a null.", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("It must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        Kangaroo kangaroo = kangaroos.get(id); //önce silinecek kanguruyu kaydettik
        kangaroos.remove(id); //listeden sildik
        return kangaroo; //silidiğimizi geri döndük böylece api çağrısnı yapan kişi angi kaydı sildiğini görebilir
    }

}
