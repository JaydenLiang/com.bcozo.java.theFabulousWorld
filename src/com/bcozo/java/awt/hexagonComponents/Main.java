package com.bcozo.java.awt.hexagonComponents;
/**
 * 
 */

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * <p>Javadoc description</p>
 * @ClassName: Main
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 14, 2016 7:50:18 PM
 *
 */
public class Main {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    /**
     * <p>This is the constructor of Main</p>
     */
    public Main() {
    }

    /**
     * <p>Description of Main</p>
     * @param args void
     */
    public static void main(String[] args) {
//        final HexagonPanel world = new HexagonPanel(5, 5);
//        final HexagonFrame frame = new HexagonFrame(world);
//        position(frame);
//        frame.init();
        final HexagonPane pane = new HexagonPane();
        final JFrame frame = new JFrame();
        final HexagonWorld world = new HexagonWorld(20, 20, 16);
        frame.add(pane);
        world.init(pane);
        position(frame);
        frame.setPreferredSize(pane.getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private static void position(final JFrame frame) {
        final Dimension size;

        size = new Dimension(600, 500);
        frame.setSize(size);
    }

}
