package com.bcozo.java.theFabulousWorld;

import java.awt.Point;

import com.bcozo.java.awt.hexagonComponents.HexagonCell;

/**
 * <p>Javadoc description</p>
 * @ClassName: Thing
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 9, 2016 2:11:13 PM
 *
 */
public class Thing  extends HexagonCell implements IThing {
    /**
     * <p>Object Unique ID.</p>
     */
    private static final long serialVersionUID = 4785172134632879906L;
    /**
     * <p>
     * The Cell the herbivore currently stays on.
     * </p>
     */
    private Cell cell;

    /**
     * <p>This is the constructor of Thing.</p>
     */
    public Thing() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#setCell(com.bcozo.java.theFabulousWorld.Cell)
     */
    @Override
    public final void setCell(Cell cell) {
        if (cell == null || cell.getThing() == this) {
            this.cell = cell;
        }
    }

    /* (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#getType()
     */
    @Override
    public int getType() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.IThing#getCellLocation()
     */
    @Override
    public Point getCellLocation() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * <p>Get the cell this thing stays on.</p>
     * @return Cell
     */
    protected Cell getCell() {
        return cell;
    }

}
