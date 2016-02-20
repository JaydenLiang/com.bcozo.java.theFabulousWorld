package com.bcozo.java.theFabulousWorld;

import java.awt.Color;
import java.util.ArrayList;

import com.bcozo.java.awt.hexagonComponents.HexagonPane;
import com.bcozo.java.awt.hexagonComponents.HexagonWorld;

/**
 * <p>
 * Javadoc description
 * </p>
 * 
 * @ClassName: World
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 7, 2016 11:38:45 PM
 */
public class World extends HexagonWorld {
    private static final int CELL_SIZE = 16;
    /**
     * <p>
     * Row count of cells on this world.
     * </p>
     */
    private int rowCount;
    /**
     * <p>
     * Column count of cells on this world.
     * </p>
     */
    private int columnCount;
    /**
     * <p>
     * The list of all cells on this world.
     * </p>
     */
    private Cell[][] cells;
    /**
     * <p>
     * The array to store all cells been modified.
     * </p>
     */
    private ArrayList<Cell> affectedCells;

    /**
     * <p>
     * This is the constructor of World.
     * </p>
     * 
     * @param row Cells per row.
     * @param column Cells per column.
     */
    public World(int row, int column) {
        super(row, column, CELL_SIZE);
        this.rowCount = row;
        this.columnCount = column;
        cells = new Cell[row][column];
        affectedCells = new ArrayList<Cell>();
    }

    /**
     * <p>
     * Get row count.
     * </p>
     * 
     * @return int row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * <p>
     * Get column count.
     * </p>
     * 
     * @return int column count.
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * <p>
     * Initializes the world class.
     * </p>
     */
    @Override
    public void init(HexagonPane hexagonPane) {
        super.init(hexagonPane);
        // initPane(hexagonPane);
        try {
            Color bgColor = new Color(0, 0, 0, 0);
            Color borderColor = new Color(0, 0, 0xff, 255);
            for (int i = 0; i < this.rowCount; i++) {
                for (int j = 0; j < this.columnCount; j++) {
                    this.cells[i][j] =
                            new Cell(this, i, j, getCellLengthOfSide());
                    this.cells[i][j].setCellBackground(bgColor);
                    this.cells[i][j].setCellBorder(borderColor);
                    this.cells[i][j].init();
                    this.getPane().add(i, j, this.cells[i][j]);
                    LivingThing livingThing =
                            CreatureFactory.createLivingThing();
                    if (livingThing != null) {
                        livingThing.setLengthOfSide(CELL_SIZE);
                        livingThing.init();
                        this.cells[i][j].setThing(livingThing);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("World.init():" + e.getMessage());
        }
    }

    /**
     * <p>
     * Set a new size to the world and its cells.
     * </p>
     * 
     * @param width the new width
     * @param height the new height
     */
    public void setSize(int width, int height) {
        // int cellWidth = width / columnCount;
        // int cellHeight = height / rowCount;
        // Dimension dimension = new Dimension(cellWidth, cellHeight);
        // for (int i = 0; i < this.rowCount; i++) {
        // for (int j = 0; j < this.columnCount; j++) {
        // this.cells[i][j].setSize(dimension);
        // }
        // }
    }

    /**
     * <p>
     * Retrieves the requested Cell from the speci ed location in the World.
     * </p>
     * 
     * @param row row number.
     * @param column column number.
     * @return a cell object
     * @throws IndexOutOfBoundsException Cell
     */
    public Cell getCellAt(int row, int column)
            throws IndexOutOfBoundsException {
        if (row < 0 || row >= this.rowCount || column < 0
                || column >= this.columnCount) {
            throw new IndexOutOfBoundsException();
        }
        return cells[row][column];
    }

    /**
     * <p>
     * Moves Herbivores one Cell.
     * </p>
     */
    public void takeTurn() {
        IThing thing;
        Cell cell;
        int index;
        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) {
                cell = this.cells[i][j];
                // when this cell is affected by other living thing. skip this.
                if (cell.isAffected()) {
                    continue;
                }
                thing = cell.getThing();
                if (thing != null && (thing instanceof LivingThing)) {
                    cell = ((ILivingThing) thing).move();
                    if (cell != null) {
                        affectedCells.add(cell);
                        cell.setAffected(true);
                    }
                }
            }
        }
        while ((index = affectedCells.size() - 1) >= 0) {
            cell = affectedCells.remove(index);
            cell.setAffected(false);
        }
        spawnLivingThings();
    }

    /**
     * <p>
     * Add more living things to the world.
     * </p>
     */
    private void spawnLivingThings() {
        ArrayList<LivingThing> lives = new ArrayList<LivingThing>();
        LivingThing live = null;
        Cell cell;
        int index;
        int row = 0;
        int col = 0;
        for(index = 0; index < 2; index ++){
            if((live = CreatureFactory.createLivingThing())!=null){
                lives.add(live);
            }
        }
        while ((index = lives.size() - 1) >= 0) {
            while (true) {
                row = RandomGenerator.nextInt(rowCount);
                col = RandomGenerator.nextInt(columnCount);
                cell = getCellAt(row, col);
                if (cell.getThing() == null) {
                    cell.setThing(lives.remove(index));
                    break;
                }
            }
        }
    }

    /**
     * <p>
     * Returns the adjacent Cells { corners only return 3 Cells, sides only
     * return 5, and all others return 8.
     * </p>
     * 
     * @return Point[] return a list of Cells
     */
    public ArrayList<Cell> getNeighbors(int row, int column) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();
        // top left
        if (row == 0 && column == 0) {
            adjacent.add(getCellAt(0, 1));
            adjacent.add(getCellAt(1, 0));
        } else if (row == 0 && column == this.columnCount - 1) {
            // top right
            adjacent.add(getCellAt(0, column - 1));
            adjacent.add(getCellAt(1, column - 1));
            if (column % 2 == 1) {
                adjacent.add(getCellAt(1, column));
            }
        } else if (row == this.rowCount - 1 && column == this.columnCount - 1) {
            // bottom right
            adjacent.add(getCellAt(row, column - 1));
            adjacent.add(getCellAt(row - 1, column));
            if (column % 2 == 0) {
                adjacent.add(getCellAt(row - 1, column - 1));
            }
        } else if (row == this.rowCount - 1 && column == 0) {
            // bottom left
            adjacent.add(getCellAt(row, column + 1));
            adjacent.add(getCellAt(row - 1, column));
            if (column % 2 == 1) {
                adjacent.add(getCellAt(row - 1, column + 1));
            }
        } else if(column == 0){
            //left side
            adjacent.add(getCellAt(row - 1, 0));
            adjacent.add(getCellAt(row - 1, 1));
            adjacent.add(getCellAt(row, 1));
            adjacent.add(getCellAt(row + 1, 0));
        } else if(row == 0){
            //top side
            adjacent.add(getCellAt(1, column - 1));
            adjacent.add(getCellAt(1, column));
            adjacent.add(getCellAt(1, column + 1));
            if (column % 2 == 1) {
                adjacent.add(getCellAt(row, column - 1));
                adjacent.add(getCellAt(row, column + 1));
            }
        } else if(column == this.columnCount - 1){
            //right side
            adjacent.add(getCellAt(row - 1, column));
            adjacent.add(getCellAt(row, column - 1));
            adjacent.add(getCellAt(row + 1, column - 1));
            adjacent.add(getCellAt(row + 1, column));
        } else if(row == this.rowCount - 1){
            //bottom side
            adjacent.add(getCellAt(row, column - 1));
            adjacent.add(getCellAt(row - 1, column));
            adjacent.add(getCellAt(row, column + 1));
            if (column % 2 == 0) {
                adjacent.add(getCellAt(row - 1, column - 1));
                adjacent.add(getCellAt(row - 1, column + 1));
            }
        }
        else {
            //middle
            adjacent.add(getCellAt(row, column - 1));
            adjacent.add(getCellAt(row - 1, column));
            adjacent.add(getCellAt(row, column + 1));
            adjacent.add(getCellAt(row + 1, column + 1));
            adjacent.add(getCellAt(row + 1, column));
            adjacent.add(getCellAt(row + 1, column - 1));
        }
        return adjacent;
    }
}
