package com.bcozo.java.theFabulousWorld;

import javax.swing.JFrame;

import com.bcozo.java.awt.hexagonComponents.HexagonPane;

/**
 * GameFrame.
 * 
 * @author BCIT
 * @version 1.0
 */
public class GameFrame extends JFrame {
    /**
     * <p>
     * Object Unique ID.
     * </p>
     */
    private static final long serialVersionUID = 1623876165767883229L;
    private final World world;

    public GameFrame(final World world) {
        this.world = world;
    }

    /**
     * <p>GameFrame Initialization.</p>
     */
    public void init() {
        final HexagonPane pane = new HexagonPane();
        world.init(pane);
        add(pane);
        setTitle("The Fabulous World");
        setPreferredSize(pane.getPreferredSize());
        addKeyListener(new TurnListener(this));
    }

    /**
     * <p>Take one turn in the world.</p>
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
