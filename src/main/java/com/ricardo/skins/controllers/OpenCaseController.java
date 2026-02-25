package com.ricardo.skins.controllers;


import com.ricardo.skins.models.Skins;
import com.ricardo.skins.models.Users;
import com.ricardo.skins.service.CaseService;
import com.ricardo.skins.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
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
            Skins rewardedSkin = caseService.openCase(caseId, userId);

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
