package com.app.skins.repositories;

import com.app.skins.models.UserSkins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<UserSkins, Long> {
    List<UserSkins> findByUserId(Long userId);

}

