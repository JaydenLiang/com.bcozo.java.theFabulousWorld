package com.bcozo.java.theFabulousWorld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>This class can call the takeTurn method in GameFrame.</p>
 * @ClassName: TurnListener
 * @author Jayden Liang
 * @version 1.0
 * @date Feb 8, 2016 1:23:50 AM
 *
 */
public class TurnListener extends KeyAdapter {
    
    /**
     * <p>The game frame reference.</p>
     */
    private GameFrame gameFrame;

    /**
     * <p>This is the constructor of TurnListener.</p>
     */
    public TurnListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.gameFrame.takeTurn();
        }
        super.keyReleased(e);
    }
}
