package com.bcozo.java.theFabulousWorld;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * <p>
 * The LivingThing class.
 * </p>
 * 
 * @ClassName: LivingThing
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 8, 2016 3:12:27 AM
 */
public abstract class LivingThing extends Thing implements ILivingThing {
    /**
     * <p>
     * Object Unique ID.
     * </p>
     */
    private static final long serialVersionUID = -7287394568804671488L;
    /**
     * <p>
     * The maximum lives this thing can have.
     * </p>
     */
    private int maxLives;
    /**
     * <p>
     * How many lives this thing left.
     * </p>
     */
    private int currentLives;

    /**
     * <p>
     * Number of generation.
     * </p>
     */
    private int generation;
    
    /**
     * <p>If this living thing has reproduced at least once.</p>
     */
    private boolean reproduced;

    /**
     * <p>
     * This is the constructor of LivingThing.
     * </p>
     */
    public LivingThing(int lives) {
        this.maxLives = lives;
        this.currentLives = lives;
        generation = 0;
    }

    /**
     * <p>
     * This is the constructor of LivingThing.
     * </p>
     */
    public LivingThing(int lives, int generation) {
        this.maxLives = lives;
        this.currentLives = lives;
        this.generation = generation;
    }

    /**
     * <p>
     * How many lives left.
     * </p>
     * 
     * @return int
     */
    public int getLives() {
        return currentLives;
    }

    public int getGeneration() {
        return generation;
    }

    /**
     * <p>
     * Lose a life by calling this method.
     * </p>
     */
    public void loseLife() {
        currentLives--;
    }

    /**
     * <p>
     * Restore the living thing's lives to its maximum.
     * </p>
     */
    public void restoreLive() {
        currentLives = maxLives;
    }

    /**
     * <p>
     * If this living thing dead or alive.
     * </p>
     * 
     * @return boolean
     */
    public boolean isDead() {
        return currentLives <= 0;
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#getType()
     */
    @Override
    public abstract int getType();

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.ILivingThing#reproduce(com.bcozo.java.theFabulousWorld.
     * ILivingThing)
     */
    @Override
    public abstract ArrayList<ILivingThing> reproduce(ILivingThing livingThing);

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#getLocation()
     */
    @Override
    public final Point getCellLocation() {
        if (this.getCell() == null) {
            return null;
        } else {
            return this.getCell().getLocation();
        }
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        //graphic.drawString(Integer.toString(currentLives), 10, 15);
    }
    
    /**
     * <p>If this living thing has reproduced at least once.</p>
     * @return boolean
     */
    public boolean isReproduced() {
        return reproduced;
    }
    
    /**
     * <p>Set the state of reproduced of this living thing.</p>
     * @param reproduced void
     */
    public void setReproduced(boolean reproduced) {
        this.reproduced = reproduced;
    }
    
    /**
     * <p>Let this living thing die at once.</p>
     */
    public void dieNow() {
        this.currentLives = 0;
    }
}
