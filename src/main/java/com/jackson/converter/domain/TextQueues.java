package com.jackson.converter.domain;

import lombok.EqualsAndHashCode;

import java.util.PriorityQueue;

@EqualsAndHashCode
public class TextQueues {
    private PriorityQueue<Alphabet> alphabets = new PriorityQueue<>();
    private PriorityQueue<Integer> numbers = new PriorityQueue<>();

    public TextQueues() {
    }

    public TextQueues(PriorityQueue<Alphabet> alphabets, PriorityQueue<Integer> numbers) {
        this.alphabets = alphabets;
        this.numbers = numbers;
    }

    public void divide(String text){
        for(int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if(isAlphabet(character)){
                alphabets.add(new Alphabet(character));
            }
            if(Character.isDigit(character)){
                numbers.add(Character.getNumericValue(character));
            }
        }
    }

    public String merge() {
        StringBuilder resultBuilder = new StringBuilder();
        while (!numbers.isEmpty() || !alphabets.isEmpty()){
            if (!alphabets.isEmpty()) {
                resultBuilder.append(alphabets.poll().toChar());
            }
            if (!numbers.isEmpty()) {
                resultBuilder.append(numbers.poll());
            }
        }
        return resultBuilder.toString();
    }

    private boolean isAlphabet(char character){
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }
}
