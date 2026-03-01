package com.app.skins.repositories;

import com.app.skins.models.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// REMOVA O IMPORT java.lang.ScopedValue;

@Repository
public interface CasesRepository extends JpaRepository<Cases, Long> {
    // Deixe vazio! O JpaRepository já te dá o findById, save, etc.
}