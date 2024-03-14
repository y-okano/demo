package com.example.passaydemo;

import org.passay.CharacterData;
import org.passay.EnglishCharacterData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public enum SpecialCharacterData implements CharacterData {

    Special("INSUFFICIENT_SPECIAL");


    private final String errorCode;
    private String characters;

    public void setSetCharacters(String setCharacters) {
        this.characters = setCharacters;
    }

    private SpecialCharacterData(String code) {
        this.errorCode = code;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getCharacters() {
        return this.characters;
    }

}
