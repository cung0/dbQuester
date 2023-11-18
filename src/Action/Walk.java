package Node.src.Action;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;

public interface Walk {
    public default void WALK_TO_AREA(Area area){
        if (area.distance(Players.getLocal().getTile()) > 6){
            if (Walking.getDestinationDistance() < 7){
                Walking.walk(area.getRandomTile());
            }
        } else if (area.distance(Players.getLocal().getTile()) <= 6){
            if (Walking.getDestinationDistance() < 1){
                Walking.walk(area.getRandomTile());
            }
        }
    }

    public default void WALK_TO_TILE(Tile tile){
        if (tile.distance(Players.getLocal().getTile()) > 8){
            if (Walking.getDestinationDistance() < 7){
                Walking.walk(tile.getTile());
            }
        } else if (tile.distance(Players.getLocal().getTile()) <= 8){
            if (Walking.getDestinationDistance() < 1){
                Walking.walk(tile.getTile());
            }
        }
    }
}
