package com.bcozo.java.theFabulousWorld;

import java.util.Random;

/**
 * RandomGenerator.
 * 
 * @author BCIT
 * @version 1.0
 */
public final class RandomGenerator {
    private static Random rand = new Random();
    private int possibilities[];
    public RandomGenerator() {
    }
    
    /**
     * <p>This is the constructor of RandomGenerator</p>
     * @param possibilities  a set of possibilities to 
     * initialize a random generator related to the set
     */
    public RandomGenerator(int possibilities[]){
        this.possibilities = possibilities;
        normalize();
    }
    
    private void normalize(){
        int i, sum = 0;
        for(i = 0; i < possibilities.length; i ++){
            sum += possibilities[i];
        }
        for(i = 0; i < possibilities.length; i ++){
            possibilities[i] = possibilities[i] * 1000 / sum;
        }
    }
    
    public static int nextInt(int bound){
        return rand.nextInt(bound);
    }
}
