package com.app.skins.controllers;


import com.app.skins.models.Skins;
import com.app.skins.service.SkinService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skins")
public class SkinController {

    @Autowired
    private SkinService skinService;

    @Operation(summary = "Find all Skins", tags = "Skin")
    @GetMapping
    public ResponseEntity<List<Skins>> findAll(){
        return ResponseEntity.ok(skinService.findAll());
    }
    @Operation(summary = "Find Skins by ID", tags = "Skin")
    @GetMapping("/{id}")
    public ResponseEntity<Skins> findById(@PathVariable Long id){
        Skins skin = skinService.findByID(id);
        return skin != null ? ResponseEntity.ok(skin) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Save a new Skins", tags = "Skin")
    @PostMapping
    public ResponseEntity<Skins> save(@RequestBody Skins skin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skinService.save(skin));
    }
    @Operation(summary = "Update Skins", tags = "Skin")
    @PutMapping("/{id}")
    public ResponseEntity<Skins> update(@RequestBody Skins skin, @PathVariable Long id){
        return  ResponseEntity.ok(skinService.update(id, skin));
    }

    @Operation(summary = "Delete Skins", tags = "Skin")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        skinService.delete(id);
    }

}
