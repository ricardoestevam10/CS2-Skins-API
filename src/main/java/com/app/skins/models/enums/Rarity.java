package com.app.skins.models.enums;

public enum Rarity {
    CONSUMER_GRADE("Common", 80.0),
    MIL_SPEC("Uncommon", 15.0),
    RESTRICTED("Rare", 4.0),
    CLASSIFIED("Mythical", 0.8),
    COVERT("Legendary", 0.15),
    GOLD("Exceedingly Rare", 0.05);

    private final String description;
    private final double defaultProbability;

    Rarity(String description, double defaultProbability) {
        this.description = description;
        this.defaultProbability = defaultProbability;
    }
}