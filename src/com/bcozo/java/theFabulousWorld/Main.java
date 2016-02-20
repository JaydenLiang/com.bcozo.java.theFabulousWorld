package com.bcozo.java.theFabulousWorld;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Main.
 * 
 * @author BCIT
 * @version 1.0
 */
public final class Main {
    /**
     * <p>The toolkit for this program.</p>
     */
    private static final Toolkit TOOLKIT;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    private Main() {
    }

    /**
     * <p>
     * Main entry method.
     * </p>
     * 
     * @param argv void
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;
        world = new World(20, 25);
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * <p>positioning this program.</p>
     * @param frame void
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(0.80f, 0.80f);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * <p>Center this program on the screen.</p>
     * @param size the size of this program.
     * @return Point
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }

    /**
     * <p>Calculate the screen's area.</p>
     * @param widthPercent percentage of width.
     * @param heightPercent percentage of height.
     * @return Dimension
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > 100.0f)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > 100.0f)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
