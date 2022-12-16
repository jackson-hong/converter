package com.jackson.converter.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.PriorityQueue;

@EqualsAndHashCode
public class Converter {

    private final String initialText;
    private String engText;
    private String numberText;

    @Builder
    public Converter(String initialText, String engText, String numberText) {
        this.initialText = initialText;
        this.engText = engText;
        this.numberText = numberText;
    }

    public Converter(String initialText) {
        this.initialText = initialText;
    }

    public void divide(){
        PriorityQueue<Alphabet> engQueue = new PriorityQueue<>();
        PriorityQueue<Integer> numberQueue = new PriorityQueue<>();
        for(int i = 0; i < initialText.length(); i++) {
            char character = initialText.charAt(i);
            if(isAlphabet(character)){
                engQueue.add(new Alphabet(character));
            }
            if(Character.isDigit(character)){
                numberQueue.add(Character.getNumericValue(character));
            }
        }
        StringBuilder engBuilder = new StringBuilder();
        StringBuilder numberBuilder = new StringBuilder();
        while (!numberQueue.isEmpty()) {
            numberBuilder.append(numberQueue.poll());
        }
        while (!engQueue.isEmpty()) {
            engBuilder.append(engQueue.poll().toChar());
        }
        engText = engBuilder.toString();
        numberText = numberBuilder.toString();
    }

    private boolean isAlphabet(char character){
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }
}
