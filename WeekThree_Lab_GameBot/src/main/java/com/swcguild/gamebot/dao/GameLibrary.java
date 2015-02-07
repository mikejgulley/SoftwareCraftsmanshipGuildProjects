package com.swcguild.gamebot.dao;

import com.swcguild.gamebot.dto.Game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Michael J. Gulley
 */
public class GameLibrary {

    private HashMap<String, Game> gameMap = new HashMap<>();
//    private SortedMap<String, Game> sortedGameMap = new TreeMap<String, Game>(gameMap);

    public Game addGame(String name, Game game) {
        return gameMap.put(name, game);
    }

    public Game removeGame(String name) {
        return gameMap.remove(name);
    }

    public Game getGame(String name) {
        return gameMap.get(name);
    }

    public ArrayList<String> getGameInventory() {
        Set<String> gameKeySet = gameMap.keySet();
        return new ArrayList<String>(gameKeySet);
    }
    
//    public Set<Entry<String, Game>> getGameInventory() {
//        return gameMap.entrySet();
//    }
}
