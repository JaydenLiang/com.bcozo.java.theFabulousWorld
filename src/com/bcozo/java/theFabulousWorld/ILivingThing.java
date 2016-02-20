package com.bcozo.java.theFabulousWorld;

import java.util.ArrayList;

public interface ILivingThing extends IThing {
    /**
     * <p>
     * Check whether this living thing preys on the other.
     * </p>
     * 
     * @param thing prey
     * @return boolean
     */
    public boolean preyOn(IThing thing);

    /**
     * <p>
     * Reproduce offsprings.
     * </p>
     */
    public ArrayList<ILivingThing> reproduce(ILivingThing livingThing);

    /**
     * <p>
     * perform the move action of a living thing. (Different type of living
     * thing may have different behaviors of movement.
     * </p>
     */
    public Cell move();
}
