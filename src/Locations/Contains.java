package Node.src.Locations;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public interface Contains {
    default boolean IS_IN_AREA(Area area){
        return area.contains(Players.getLocal());
    }
    default boolean IS_ON_TILE(Tile tile){
        return tile.distance(Players.getLocal()) < 1;
    }

}
