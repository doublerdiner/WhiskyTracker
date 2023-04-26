package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name="year", required = false)Integer year,
            @RequestParam(name="distillery", required = false)String distillery,
            @RequestParam(name="age", required = false)Integer age,
            @RequestParam(name="region", required = false)String region,
            @RequestParam(name="distilleryid", required = false)Integer distilleryid
    ){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        else if(distillery != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryNameAndAge(distillery, age), HttpStatus.OK);
        }
        else if(distilleryid != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryAndAge(distilleryRepository.getById(Long.valueOf(distilleryid)), age), HttpStatus.OK);
        }
        else if(region != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies/{id}")
    public ResponseEntity<Optional<Whisky>> getWhisky(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/whiskies")
    public ResponseEntity saveWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }


}
