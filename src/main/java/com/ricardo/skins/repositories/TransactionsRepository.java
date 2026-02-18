package com.ricardo.skins.repositories;

import com.ricardo.skins.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    // Busca todas as transações de um usuário específico (Extrato)
    // O Spring entende que "User" é o objeto e "Id" é o campo dentro dele
    List<Transactions> findByUserId(Long userId);

    // Busca por tipo (ex: listar apenas "DEPOSIT" ou "PURCHASE")
    List<Transactions> findByType(String type);

    // Busca as transações mais recentes primeiro
    List<Transactions> findByUserIdOrderByTimestampDesc(Long userId);
}