package com.app.skins.repositories;

import com.app.skins.models.Skins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkinsRepository extends JpaRepository<Skins, Long> {

    // Procura skins que estão disponíveis para venda (isAvailable = true)
    List<Skins> findByIsAvailableTrue();

    // Procura skins pelo nome exato da Steam (assetId) para evitar duplicados
    Optional<Skins> findByAssetId(String assetId);

    // Função de pesquisa: Procura por nome ignorando maiúsculas/minúsculas
    // Útil para quando o utilizador pesquisar "ak-47" ou "AK-47"
    List<Skins> findByMarketNameContainingIgnoreCase(String marketName);

    // Procura todas as skins de uma categoria específica (ex: "Knife", "Sniper Rifle")
    List<Skins> findByWeaponType(String weaponType);
}