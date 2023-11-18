package Node.src.Objects;

import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.wrappers.items.GroundItem;

public interface GroundObj {
    public default GroundItem getGroundObject(String objectName){
        if (GroundItems.closest(objectName) != null) {
            return GroundItems.closest(objectName);
        }
        return null;
    }

    public default GroundItem getGroundObject(int objectID){
        if (GroundItems.closest(objectID) != null) {
            return GroundItems.closest(objectID);
        }
        return null;
    }
}
