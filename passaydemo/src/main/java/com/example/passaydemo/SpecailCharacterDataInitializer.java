package com.example.passaydemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpecailCharacterDataInitializer {
    @Value("${any.key1}")
    private String characters;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeSpecialCharacterData() {
        SpecialCharacterData.Special.setSetCharacters(characters);
    }
}
