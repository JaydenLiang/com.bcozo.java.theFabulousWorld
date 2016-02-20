package com.bcozo.java.theFabulousWorld;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>
 * The Herbivore class.
 * </p>
 * 
 * @ClassName: Herbivore
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 8, 2016 1:10:14 AM
 */
public class Herbivore extends LivingThing implements ILivingThing {
    /**
     * <p>
     * Object unique ID.
     * </p>
     */
    private static final long serialVersionUID = -5028276921108622843L;
    /**
     * <p>
     * Background color.
     * </p>
     */
    private static final int GRAPHIC_BACKGROUND_COLOR = 0xFFFF00;
    /**
     * <p>
     * How many lives this living thing can lose.
     * </p>
     */
    public static final int LIVE_NUMBER = 5;

    /**
     * <p>
     * This is the constructor of Herbivore.
     * </p>
     */
    public Herbivore() {
        super(LIVE_NUMBER);
    }

    /**
     * <p>
     * This is the constructor of Herbivore.
     * </p>
     * 
     * @param generation the generation of offsprings.
     */
    public Herbivore(int generation) {
        super(LIVE_NUMBER, generation);
        init();
        setBackground(Color.ORANGE);
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.LivingThing#init()
     */
    @Override
    public void init() {
        super.init();
        setCellBackground(new Color(GRAPHIC_BACKGROUND_COLOR));
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#getType()
     */
    @Override
    public int getType() {
        return TYPE_LIVING_THING_HERBIVORE;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.bcozo.java.theFabulousWorld.ILivingThing#preyOn(com.bcozo.java.theFabulousWorld.IThing)
     */
    @Override
    public boolean preyOn(IThing thing) {
        return thing != null
                && thing.getType() == TYPE_LIVING_THING_PLANT;
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.LivingThing#reproduce(com.bcozo.java.theFabulousWorld.
     * ILivingThing)
     */
    @Override
    public ArrayList<ILivingThing> reproduce(ILivingThing livingThing) {
        ArrayList<ILivingThing> offspring;
        Herbivore child;
        if (livingThing.getType() == TYPE_LIVING_THING_HERBIVORE) {
            offspring = new ArrayList<ILivingThing>();
            for (int i = 0; i < 2; i++) {
                child = new Herbivore(getGeneration() + 1);
                offspring.add(child);
            }
            return offspring;
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.LivingThing#move()
     */
    @Override
    public Cell move() {
        boolean canReproduce = false;
        ArrayList<Cell> adjacent = this.getCell().getAdjacentCells();
        Cell nextCell = null;
        int index;
        boolean canMove = false;
        boolean hasReproduced = false;
        LivingThing livingThing = null;
        ArrayList<ILivingThing> offsprings = null;
        GraveStone graveStone = null;
        for (int i = 0; i < adjacent.size(); i++) {
            index = RandomGenerator.nextInt(adjacent.size() - 1);
            nextCell = adjacent.get(index);
            if (nextCell.getThing() == null) {
                this.loseLife();
                canMove = true;
                break;
            } else if (preyOn(nextCell.getThing())) {
                this.restoreLive();
                canMove = true;
                break;
            } else if (canReproduce && (nextCell.getThing() instanceof LivingThing) && (offsprings =
                    reproduce((ILivingThing) nextCell.getThing())) != null) {
                canMove = true;
                hasReproduced = true;
                break;
            }
        }
        if (hasReproduced) {
            if (offsprings != null) {
                placeOffsprings(offsprings);
            }
            // both it and the herbivore in the nextCell die and leave a grave
            // stone in this cell.
            this.dieNow();
            if (nextCell != null) {
                livingThing = (LivingThing) (nextCell.getThing());
                livingThing.dieNow();
                nextCell.removeThing();
            }
            graveStone = new GraveStone();
            getCell().setThing(graveStone);
        } else if (!canMove) {
            // the worst situation when this herbivore is surrounded by all things
            // it cannot eat. It ends up staying in the current cell and lose one
            // life.
            this.loseLife();
            // if is dead, clean this cell.
            if (isDead()) {
                getCell().removeThing();
            }
        } else {
            // if is dead, clean this cell.
            if (isDead()) {
                getCell().removeThing();
            } else {
                nextCell.setThing(this);
                return nextCell;
            }
        }
        return null;
    }

    private void placeOffsprings(ArrayList<ILivingThing> offsprings) {
        if (offsprings == null) {
            return;
        }
        ArrayList<Cell> adjacents = this.getCell().getAdjacentCells();
        Iterator<Cell> iterator = adjacents.iterator();
        Cell cell = null;
        while (iterator.hasNext()) {
            cell = iterator.next();
            if (cell.getThing() == null) {
                cell.setThing((LivingThing) (offsprings.remove(0)));
                if (offsprings.size() == 0) {
                    return;
                }
            }
        }
        return;
    }
}
