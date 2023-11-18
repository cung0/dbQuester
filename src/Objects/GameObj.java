package Node.src.Objects;


import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;

public interface GameObj {
    public default GameObject getGameObject(String objectName){
        if (GameObjects.closest(objectName) != null) {
            return GameObjects.closest(objectName);
        }
        return null;
    }

    public default GameObject getGameObject(int objectID){
        if (GameObjects.closest(objectID) != null) {
            return GameObjects.closest(objectID);
        }
        return null;
    }
}
