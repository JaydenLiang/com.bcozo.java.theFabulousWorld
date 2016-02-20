package com.bcozo.java.theFabulousWorld;

import java.awt.Color;

/**
 * <p>Javadoc description</p>
 * @ClassName: GraveStone
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 9, 2016 2:07:28 PM
 *
 */
public class GraveStone extends NoneLivingThing {
    /**
     * <p>Object unique ID.</p>
     */
    private static final long serialVersionUID = -6123620542160057307L;

    /**
     * <p>Background color.</p>
     */
    private static final int GRAPHIC_BACKGROUND_COLOR = 0xAAAAAA;
    /**
     * <p>This is the constructor of GraveStone.</p>
     */
    public GraveStone() {
        init();
    }
    /* (non-Javadoc)
     * @see com.bcozo.java.theFabulousWorld.LivingThing#init()
     */
    @Override
    public void init() {
        super.init();
        setBackground(new Color(GRAPHIC_BACKGROUND_COLOR));
    }

    @Override
    public int getType() {
        return TYPE_NONE_LIVINING_GRAVE_STONE;
    }
}
