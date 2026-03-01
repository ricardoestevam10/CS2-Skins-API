package com.app.skins.repositories;

import com.app.skins.models.UserSkins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserSkinsRepository extends JpaRepository<UserSkins, Long> {

    // O método principal: "Me mostre o inventário do usuário X"
    List<UserSkins> findByUserId(Long userId);

    // Caso você queira saber quem são todos os donos de uma skin específica (raro, mas útil)
    List<UserSkins> findBySkinId(Long skinId);
}