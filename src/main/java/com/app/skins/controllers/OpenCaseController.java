package com.app.skins.controllers;


import com.app.skins.models.UserSkins;
import com.app.skins.models.Users;
import com.app.skins.service.CaseService;
import com.app.skins.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;
import java.math.BigDecimal;

@RestController
public class OpenCaseController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> open(@RequestParam Long caseId, @RequestParam Long userId){
        try{
            UserSkins rewardedSkin = caseService.openCase(caseId, userId);

            return  ResponseEntity.ok(rewardedSkin);
        }catch (RuntimeErrorException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @Operation(summary = "Deposit in web", tags = "Deposit")
    @PatchMapping("/{id}/deposit")
    public ResponseEntity<Users> deposit(@PathVariable Long id, @RequestParam BigDecimal amount){
        Users updatedUser = userService.deposit(id, amount);

        return ResponseEntity.ok(updatedUser);
    }


}
