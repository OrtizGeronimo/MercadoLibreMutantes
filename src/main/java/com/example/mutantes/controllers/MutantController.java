package com.example.mutantes.controllers;

import com.example.mutantes.domain.Mutant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mutantes.services.MutantService;

@RestController
@RequestMapping(path = "apiMutants/v1/mutant")
@CrossOrigin(origins = "*")
public class MutantController {

    @Autowired
    protected MutantService service;

    @PostMapping("")
    public ResponseEntity<?> comprobarAdn(@RequestBody Mutant m) {
        try {
            if (service.comprobarMutante(m)) {
                return ResponseEntity.status(HttpStatus.OK).body("");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getCount(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getCount());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

}
