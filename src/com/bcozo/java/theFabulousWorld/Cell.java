package com.bcozo.java.theFabulousWorld;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.bcozo.java.awt.hexagonComponents.HexagonCell;

/**
 * <p>
 * Represented as a square in the world.
 * </p>
 * 
 * @ClassName: Cell
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 7, 2016 11:54:07 PM
 */
public class Cell extends HexagonCell {
    /**
     * <p>
     * Object Unique ID.
     * </p>
     */
    private static final long serialVersionUID = -8270440106895900118L;
    /**
     * <p>
     * Border color.
     * </p>
     */
    private static final int GRAPHIC_BORDER_COLOR = 0x000000;
    /**
     * <p>
     * Border thickness.
     * </p>
     */
    private static final int GRAPHIC_BORDER_THICKNESS = 1;
    /**
     * <p>
     * The row number of this cell location on the world.
     * </p>
     */
    private int row;
    /**
     * <p>
     * The column number of this cell location on the world.
     * </p>
     */
    private int column;
    /**
     * <p>
     * The world that holds this cell.
     * </p>
     */
    private World world;
    /**
     * <p>
     * The thing that currently stays on this cell.
     * </p>
     */
    private Thing thing;
    /**
     * <p>
     * State of this cell's affected.
     * </p>
     */
    private boolean affected;

    /**
     * <p>
     * This is the constructor of Cell.
     * </p>
     * 
     * @param world the world that holds this cell
     * @param row the location on its world
     * @param column the location on its world
     */
    public Cell(World world, int row, int column, int size) {
        super(size);
        this.world = world;
        this.row = row;
        this.column = column;
    }

    /**
     * <p>
     * State for cell is affected.
     * </p>
     * 
     * @return boolean
     */
    public boolean isAffected() {
        return affected;
    }

    /**
     * <p>
     * Set the state for cell is affected.
     * </p>
     * 
     * @param affected state
     */
    public void setAffected(boolean affected) {
        this.affected = affected;
    }

    /**
     * <p>
     * Returns the location of the Cell on the World.
     * </p>
     * 
     * @return Point the location of (row, column)
     */
    @Override
    public Point getLocation() {
        return new Point(row, column);
    }

    /**
     * <p>
     * Returns the adjacent Cells { corners only return 3 Cells, sides only
     * return 5, and all others return 8.
     * </p>
     * 
     * @return Point[] return a list of Cells
     */
    public ArrayList<Cell> getAdjacentCells() {
        return this.world.getNeighbors(this.row, this.column);
    }

    /**
     * <p>
     * Set an IThing object on this cell. Ignore null.
     * </p>
     * 
     * @param thing an object that can place on a cell.
     */
    public void setThing(Thing thing) {
        if (thing != null) {
            Point location = thing.getCellLocation();
            Cell tempCell;
            if (location != null) {
                tempCell = this.world.getCellAt(location.x, location.y);
                tempCell.removeThing();
            }
            if (this.thing != null) {
                this.thing.setCell(null);
                this.remove(this.thing);
            }
            this.thing = thing;
            this.thing.setCell(this);
            this.add(thing);
            this.setCellBackground(thing.getCellBackground());
          Dimension dimension = getPreferredSize();
//          dimension.width -= 3;
//          dimension.height -= 3;
          thing.setSize(dimension);
          thing.setLocation(0, 0);
        } else {
            this.setCellBackground(null);
        }
    }

    /**
     * <p>
     * Get the IThing object on this cell.
     * </p>
     * 
     * @return IThing the object that is placed on this cell.
     */
    public IThing getThing() {
        return this.thing;
    }

    /**
     * <p>
     * Remove the IThing object from this cell.
     * </p>
     */
    public void removeThing() {
        if (this.thing != null) {
            this.thing.setCell(null);
        }
        this.remove(this.thing);
        this.thing = null;
        this.setCellBackground(null);
    }

    /**
     * <p>
     * Return a rectangle containing location information of this cell.
     * </p>
     * 
     * @return Rectangle
     */
    public Rectangle getDrawLocation() {
        Rectangle rectangle = new Rectangle();
        rectangle.width = this.getWidth();
        rectangle.height = this.getHeight();
        rectangle.x = super.getLocation().x;
        rectangle.y = super.getLocation().y;
        return rectangle;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Component#setSize(java.awt.Dimension)
     */
    @Override
    public void setSize(Dimension arg0) {
        super.setSize(arg0);
        this.setPreferredSize(arg0);
        if (this.thing != null) {
            this.thing.setPreferredSize(arg0);
        }
    }
}
