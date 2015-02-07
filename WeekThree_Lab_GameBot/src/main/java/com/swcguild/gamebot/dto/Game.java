package com.swcguild.gamebot.dto;

import com.swcguild.gamebot.ui.View;

/**
 *
 * @author Michael J. Gulley
 */
public interface Game {
    View io = new View();
    
    public String getGameName();
    public void run();
}
