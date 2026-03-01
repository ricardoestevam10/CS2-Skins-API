package com.app.skins.controllers;

import com.app.skins.models.UserSkins;
import com.app.skins.repositories.UserSkinsRepository;
import com.app.skins.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private UserSkinsRepository userSkinsRepository;

    @Autowired
    private CaseService caseService;

    // LISTAR INVENTÁRIO (O Front vai usar isso para mostrar a página do perfil)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserSkins>> getInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(userSkinsRepository.findByUserId(userId));
    }

    // VENDER SKIN (O Front vai chamar isso no clique do botão)
    @PostMapping("/sell/{userSkinId}")
    public ResponseEntity<String> sellSkin(@PathVariable Long userSkinId) {
        caseService.sellSkin(userSkinId);
        return ResponseEntity.ok("Skin vendida com sucesso!");
    }
}