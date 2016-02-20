package com.bcozo.java.theFabulousWorld;

import java.awt.Point;

public interface IThing {
    /**
     * <p>Type of grave stone.</p>
     */
    public static final int TYPE_NONE_LIVINING_GRAVE_STONE = 99;
    /**
     * <p>
     * Type of Plant.
     * </p>
     */
    public static int TYPE_LIVING_THING_PLANT = 1;
    /**
     * <p>
     * Type of Herbivore.
     * </p>
     */
    public static int TYPE_LIVING_THING_HERBIVORE = 2;
    /**
     * <p>
     * Puts this object on the specified Cell. ONLY can put the cell that
     * contains this object. Make sure it's a one way operation. Clear when set
     * to null.
     * </p>
     * 
     * @param cell the cell to put this object on.
     */
    public void setCell(Cell cell);

    /**
     * <p>Return the type of this thing.</p>
     * @return int
     */
    public int getType();

    /**
     * <p>
     * Get the location of the cell of this object.
     * </p>
     */
    public Point getCellLocation();
}
