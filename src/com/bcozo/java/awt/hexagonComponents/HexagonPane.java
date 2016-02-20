/**
 * 
 */
package com.bcozo.java.awt.hexagonComponents;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLayeredPane;

/**
 * <p>
 * Javadoc description
 * </p>
 * 
 * @ClassName: HexagonPane
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 16, 2016 7:50:32 PM
 */
public class HexagonPane extends JLayeredPane {
    private HexagonCell cells[][];
    private int rowCount;
    private int columnCount;
    private int cellLengthOfSide;
    private int halfHeight;

    /**
     * <p>
     * This is the constructor of HexagonPane
     * </p>
     */
    public HexagonPane(){
        
    }
    
    public int getRowCount() {
        return rowCount;
    }
    
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
    
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
    public int getCellLengthOfSide() {
        return cellLengthOfSide;
    }
    
    public void setCellLengthOfSide(int cellLengthOfSide) {
        this.cellLengthOfSide = cellLengthOfSide;
    }
    
    public void init(){
        cells = new HexagonCell[rowCount][columnCount];
        setBounds(0, 0, (2 * columnCount - 1 / 2) * this.cellLengthOfSide,
                (rowCount + 1 / 2) * HexagonCell.getCellHeight(cellLengthOfSide));
        addMouseMotionListener(new HexagonCellListener(this));
    }

    @Override
    public Component add(Component comp) {
        throw new IllegalArgumentException(
                "Please do NOT directly invoke this method"
                        + ", use add(HexagonPanel panel) Instead.");
    }

    public HexagonCell add(int row, int col, HexagonCell hexagonCell) throws Exception {
        HexagonCell hexagon = getHexagonAt(row, col);
        if(hexagon != null){
            remove(row, col);
        }
        Component comp = super.add(hexagonCell);
        if(!(comp instanceof HexagonCell)){
            throw new Exception("Cannot add hexagon at ("+row+","+col+")");
        }
        Point pos = getCellPosition(row, col);
        hexagonCell.setBounds(pos.x, pos.y, hexagonCell.getCellWidth(), hexagonCell.getCellHeight());
        return hexagonCell;
    }
    
    public void remove(int row, int col) throws Exception {
        HexagonCell hexagonCell = getHexagonAt(row, col);
        if(hexagonCell == null){
            throw new Exception("Cannot remove hexagon at ("+row+","+col+")");
        }
        super.remove(hexagonCell);
    }
    
    public HexagonCell getHexagonAt(int row, int col){
        return cells[row][col];
    }
    
    private HexagonCell getHexagonAtPoint(int x, int y){
        return null;
    }

    public Point getCellPosition(int row, int col) {
        int x, y;
        x = (HexagonCell.getCellWidth(this.cellLengthOfSide)
                - HexagonCell.getCellWidth(this.cellLengthOfSide) / 4) * col;
        if (col % 2 == 0) {
            y = HexagonCell.getCellHeight(this.cellLengthOfSide) * row;
        } else {
            y = HexagonCell.getCellHeight(this.cellLengthOfSide) * row
                    + HexagonCell.getCellHeight(this.cellLengthOfSide) / 2;
        }
        return new Point(x, y);
    }
//    
//    @Override
//    protected void paintComponent(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRect(10, 10, 100, 100);
//        super.paintComponent(g);
//    }
    
    private class HexagonCellListener extends MouseMotionAdapter {
        private HexagonPane hexagonPane;
        /**
         * <p>This is the constructor of HexagonCellListener</p>
         */
        public HexagonCellListener(HexagonPane hexagonPane) {
            this.hexagonPane = hexagonPane;
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            HexagonCell cell = getHexagonAtPoint(event.getX(), event.getY());
            if(cell != null){
                cell.onMouseMove(this.hexagonPane);
            }
            super.mouseMoved(event);
        }
    }
}
