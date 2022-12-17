package com.jackson.converter.domain;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

@EqualsAndHashCode
@Slf4j
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
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if(isAlphabet(character)){
                alphabets.add(new Alphabet(character));
                stringBuilder.append(character);
            }
            if(Character.isDigit(character)){
                numbers.add(Character.getNumericValue(character));
                stringBuilder.append(character);
            }
        }
        log.info("text with eng and num : {}", stringBuilder.toString());
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

    public static boolean isAlphabet(char character){
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }
}
