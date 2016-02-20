package com.bcozo.java.theFabulousWorld;

/**
 * <p>
 * Javadoc description
 * </p>
 * 
 * @ClassName: CreatureFactory
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 8, 2016 1:19:11 PM
 */
public class CreatureFactory {

    /**
     * <p>
     * This is the constructor of CreatureFactory.
     * </p>
     */
    public CreatureFactory() {
    }

    /**
     * <p>
     * Randomly create a living thing object.
     * </p>
     * 
     * @return LivingThing
     */
    public static LivingThing createLivingThing() {
        LivingThing livingThing = null;
        int seed = RandomGenerator.nextInt(100);
        if (seed <= 10) {
            livingThing = new Herbivore();
        } else if (seed <= 40) {
            livingThing = new Plant();
        }
        return livingThing;
    }
}
