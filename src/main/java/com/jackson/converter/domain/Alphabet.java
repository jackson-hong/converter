package com.jackson.converter.domain;

public class Alphabet implements Comparable<Alphabet>{
    private final Character character;

    public Alphabet(Character character) {
        this.character = character;
    }

    @Override
    public int compareTo(Alphabet o) {
        char upperSource = Character.toUpperCase(character);
        char upperTarget = Character.toUpperCase(o.character);
        if(upperSource == upperTarget) {
            return o.character <= character ? 1 : -1;
        }
        return upperTarget < upperSource ? 1 : -1;
    }

    public Character toChar() {
        return character;
    }
}
