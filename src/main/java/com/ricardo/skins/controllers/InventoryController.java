package com.ricardo.skins.controllers;


import com.ricardo.skins.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private CaseService caseService;

    @PostMapping("/sell/{userSkinId}")
    public ResponseEntity<String> sellSkin(@PathVariable Long userSkinId){
        caseService.sellSkin(userSkinId);
        return ResponseEntity.ok("Skin vendida com sucesso! O valor foi adicionado ao seu saldo.");
    }

}
