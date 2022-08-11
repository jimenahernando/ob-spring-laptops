package com.jimenahernando.entrega_1.controller;

import com.jimenahernando.entrega_1.entities.Laptop;
import com.jimenahernando.entrega_1.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    @ApiOperation(value = "Lista todas las laptops registradas")
    public ResponseEntity<List<Laptop>> findAll(){
        List<Laptop> laptops =  repository.findAll();
//        if(laptops.size() != 0)
//            return new ResponseEntity<>(laptops, HttpStatus.OK);
//
//        return new ResponseEntity<>(laptops, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(laptops, HttpStatus.OK);
    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation(value = "Muestra información de la laptop buscada")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> optLaptop =  repository.findById(id);
        if(optLaptop.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(optLaptop.get(), HttpStatus.OK);
    }

    @PostMapping("/api/laptops")
    @ApiOperation(value = "Registra una nueva laptop en el sistema")
    public ResponseEntity<Laptop> create(@RequestBody Laptop body){
        if(body.getId() != null){
            log.warn("No se puede crear una laptop con id ya asignado");
            return ResponseEntity.badRequest().build();
        }
        Laptop newLaptop = repository.save(body);

        return new ResponseEntity<>(newLaptop, HttpStatus.OK);
    }

    @PutMapping("/api/laptops")
    @ApiOperation(value = "Actualiza información de una laptop registrada")
    public ResponseEntity<Laptop> update(@RequestBody Laptop body){
        if(body.getId() == null){
            log.warn("No se puede actualizar una laptop inexistente");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean laptopExist = repository.existsById(body.getId());
        if (!laptopExist) {
            log.warn("Laptop no encontrada");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Laptop laptop = repository.save(body);

        return new ResponseEntity<>(laptop, HttpStatus.OK);
    }

    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation(value = "Elimina del sistema la laptop con el id indicado")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        boolean laptopExist = repository.existsById(id);
        if (!laptopExist) {
            log.warn("Laptop no encontrada");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/laptops")
    @ApiOperation(value = "Elimina todas las laptops registradas al momento")
    public ResponseEntity<Laptop> deleteAll(){
        if(repository.count() <= 0){
            log.warn("No hay laptops registradas");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
