package com.example.passaydemo;

import org.passay.CharacterData;
import org.springframework.beans.factory.annotation.Value;

public class ExCharacterData implements CharacterData {

    private final String errorCode;

    @Value("${any.key1}")
    private final String characters;

    public ExCharacterData(String characters) {
        this.errorCode = "SPECIAL";
        this.characters = characters;
    }

    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getCharacters() {
        return null;
    }
}
