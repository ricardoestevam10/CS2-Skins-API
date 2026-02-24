package com.ricardo.skins.service;

import com.ricardo.skins.models.Cases;
import com.ricardo.skins.models.Skins;
import com.ricardo.skins.models.Users;
import com.ricardo.skins.repositories.CasesRepository;
import com.ricardo.skins.repositories.UsersRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CaseService {

    @Autowired
    private CasesRepository casesRepository;

    @Autowired // Faltava essa anotação aqui!
    private UsersRepository usersRepository;

    @Transactional
    public Skins openCase(Long caseId, Long userId) {
        // 1. Busca o usuário
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // 2. Busca a caixa
        Cases box = casesRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrada"));

        // 3. Valida saldo
        if (user.getBalance().compareTo(box.getPrice()) < 0) {
            throw new RuntimeException("Saldo insuficiente! Você precisa de R$" + box.getPrice());
        }

        // 4. Deduz o saldo (O Hibernate salvará isso automaticamente ao fim da transação)
        user.setBalance(user.getBalance().subtract(box.getPrice()));
        usersRepository.save(user);

        // 5. Lógica de sorteio
        List<Skins> skinsInBox = box.getSkins();
        if (skinsInBox.isEmpty()) {
            throw new RuntimeException("Esta caixa está vazia!");
        }

        int totalWeight = skinsInBox.stream().mapToInt(Skins::getWeight).sum();
        int randomValue = new Random().nextInt(totalWeight);
        int currentSum = 0;

        for (Skins skin : skinsInBox) {
            currentSum += skin.getWeight();
            if (randomValue < currentSum) {
                return skin;
            }
        }

        return skinsInBox.get(0);
    }
}