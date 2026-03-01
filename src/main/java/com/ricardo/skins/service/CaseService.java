package com.ricardo.skins.service;

import com.ricardo.skins.models.Cases;
import com.ricardo.skins.models.Skins;
import com.ricardo.skins.models.Users;
import com.ricardo.skins.models.UserSkins; // Importe seu model de inventário
import com.ricardo.skins.models.enums.SkinsStatus;
import com.ricardo.skins.repositories.CasesRepository;
import com.ricardo.skins.repositories.UsersRepository;
import com.ricardo.skins.repositories.UserSkinsRepository; // Importe o repositório
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CaseService {

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserSkinsRepository userSkinsRepository; // Adicionado para salvar o inventário

    @Transactional
    public Skins openCase(Long caseId, Long userId) {
        // 1. Busca o usuário
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // 2. Busca a caixa
        Cases caseEntity = casesRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found!"));

        // 3. Valida saldo
        if (user.getBalance().compareTo(caseEntity.getPrice()) < 0) {
            throw new RuntimeException("Insufficient balance! You need R$" + caseEntity.getPrice());
        }

        // 4. Deduz o saldo
        user.setBalance(user.getBalance().subtract(caseEntity.getPrice()));
        usersRepository.save(user);

        // 5. Lógica de sorteio
        List<Skins> availableSkins = caseEntity.getSkins();
        if (availableSkins.isEmpty()) {
            throw new RuntimeException("This case is empty!");
        }

        int totalWeight = availableSkins.stream().mapToInt(Skins::getWeight).sum();
        int randomValue = new Random().nextInt(totalWeight);
        int currentWeightSum = 0;

        Skins wonSkin = null;

        for (Skins skin : availableSkins) {
            currentWeightSum += skin.getWeight();
            if (randomValue < currentWeightSum) {
                wonSkin = skin;
                break;
            }
        }

        // Fallback caso algo falhe no loop
        if (wonSkin == null) wonSkin = availableSkins.get(0);

        UserSkins inventoryEntry = new UserSkins();
        inventoryEntry.setUser(user);
        inventoryEntry.setSkin(wonSkin);
        inventoryEntry.setSkinName(wonSkin.getMarketName());

        inventoryEntry.setStatus(SkinsStatus.WAITING);
        inventoryEntry.setPriceAtTime(wonSkin.getPrice());

        userSkinsRepository.save(inventoryEntry);

        return wonSkin;
    }

    @Transactional
    public void sellSkin(Long userSkinId){
        UserSkins userSkins = userSkinsRepository.findById(userSkinId).orElseThrow(() -> new RuntimeException("Skin não encontrada no inventário!"));
        Users user = userSkins.getUser();
        user.setBalance(user.getBalance().add(userSkins.getPriceAtTime()));
        userSkinsRepository.delete(userSkins);
        usersRepository.save(user);
    }
}