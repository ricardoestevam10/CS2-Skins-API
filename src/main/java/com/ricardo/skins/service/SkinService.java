package com.ricardo.skins.service;


import com.ricardo.skins.models.Skins;
import com.ricardo.skins.repositories.SkinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkinService {
    @Autowired
    private SkinsRepository skinsRepository;

    public List<Skins> findAll(){ return skinsRepository.findAll(); }

    public Skins findByID(Long id){
       return skinsRepository.findById(id).orElse(null);
    }

    public void delete(Long id){
        Skins skin = new Skins();
        skin.setId(id);
        skinsRepository.delete(skin);
    }

    public Skins save(Skins skin){ return skinsRepository.save(skin);}

    public Skins update(Long id, Skins skinDetails) {
        Skins skin = findByID(id);
        if (skin != null) {
            skin.setMarketName(skinDetails.getMarketName());
            skin.setPrice(skinDetails.getPrice());
            skin.setIsAvailable(skinDetails.getIsAvailable());
            return skinsRepository.save(skin);
        }
        return null;
    }
}
