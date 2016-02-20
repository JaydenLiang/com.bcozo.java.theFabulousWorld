/**
 * 
 */
package com.bcozo.java.awt.hexagonComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * <p>
 * Javadoc description
 * </p>
 * 
 * @ClassName: HexagonCell
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 14, 2016 8:02:17 PM
 */
public class HexagonCell extends JPanel {
    private static final double radian = Math.toRadians(60);
    private static int sid = 0;
    private int lengthOfSide;
    private CellShape cellShape;
    private Color fgColor;
    private Color bgColor;
    private Color borderColor;
    private int opacity;
    private Polygon hexagon;
    private int id;
    private boolean initialized;

    /**
     * <p>
     * This is the constructor of HexagonCell
     * </p>
     */
    public HexagonCell(int lengthOfSide) {
        id = ++sid;
        setCellShape(CellShape.FLAT);
        this.lengthOfSide = lengthOfSide;
        this.hexagon = new Polygon();
        this.opacity = 255;
        super.setBackground(new Color(0, 0, 0, 0));
    }

    public HexagonCell() {
        id = ++sid;
        setCellShape(CellShape.FLAT);
        this.hexagon = new Polygon();
        this.opacity = 255;
        super.setBackground(new Color(0, 0, 0, 0));
    }

    public void setCellShape(CellShape shape) {
        this.cellShape = shape;
    }

    public CellShape getCellShape() {
        return cellShape;
    }

    public int getCellWidth() {
        return getCellWidth(this.lengthOfSide);
    }

    public double getHalfCellHeight() {
        return lengthOfSide * Math.sin(radian);
    }

    public int getCellHeight() {
        return getCellHeight(this.lengthOfSide);
    }

    public static int getCellWidth(int lengthOfSide) {
        return lengthOfSide * 2;
    }

    public static int getCellHeight(int lengthOfSide) {
        return (int) (lengthOfSide * Math.sin(radian) * 2);
    }
    
    public void setCellBackground(Color bgColor) {
        this.bgColor = bgColor;
    }
    
    public Color getCellBackground(){
        return this.bgColor;
    }

    public void setCellForeground(Color fgColor) {
        this.fgColor = fgColor;
    }
    
    public Color getCellForeground(){
        return this.fgColor;
    }

    public void setCellBorder(Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public void setLengthOfSide(int lengthOfSide) {
        this.lengthOfSide = lengthOfSide;
    }
    
    public int getLengthOfSide() {
        return lengthOfSide;
    }

    public void init() {
        this.hexagon.addPoint(0, (int) getHalfCellHeight());
        this.hexagon.addPoint(getCellWidth() / 4, 0);
        this.hexagon.addPoint(getCellWidth() - getCellWidth() / 4, 0);
        this.hexagon.addPoint(getCellWidth(), (int) getHalfCellHeight());
        this.hexagon.addPoint(getCellWidth() - getCellWidth() / 4,
                getCellHeight());
        this.hexagon.addPoint(getCellWidth() / 4, getCellHeight());
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.initialized = true;
    }

    public void setPosition(int x, int y) {
        setBounds(x, y, getCellWidth(), getCellHeight());
    }

    private void drawBorder(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        if (this.cellShape == CellShape.FLAT) {
            g2d.setColor(this.borderColor);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawPolygon(this.hexagon);
        } else if (this.cellShape == CellShape.POINTY) {

        }
    }

    private void drawBackground(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        if (this.cellShape == CellShape.FLAT) {
            if(this.bgColor == null){
                g2d.setColor(this.getBackground());
            }
            else{
                g2d.setColor(this.bgColor);
            }
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillPolygon(hexagon);
        } else if (this.cellShape == CellShape.POINTY) {

        }
    }

    public void onMouseMove(HexagonPane hexagonPane) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!this.initialized){
            System.out.println("HexagonCell.paintComponent(): NOT initialized.");
        }
        drawBackground(g);
        drawBorder(g);
    }

    public enum CellShape {
        FLAT, POINTY
    }

    public enum CellForm {
        FORMA, FORMB
    }
}
