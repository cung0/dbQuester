package Node.src.Objects;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

public interface Interact extends GameObj, GroundObj, Npc{


    public default void interactGameObj(GameObject obj, String interactString){
        if (obj != null) {
            obj.interact(interactString);
        }
    }

    public default void interactGameObj(GameObject obj, String interactString, boolean condition){
        if (obj != null) {
            if (obj.interact(interactString)){
                Sleep.sleepUntil(() -> condition, 5000);
            }
        }
    }

    public default void interactGroundObj(GroundItem obj, String interactString){
        if (obj != null) {
            obj.interact(interactString);
        }
    }

    public default void interactGroundObj(GroundItem obj, String interactString, boolean condition){
        if (obj != null) {
            if (obj.interact(interactString)){
                Sleep.sleepUntil(() -> condition, 5000);
            }
        }
    }

    public default void interactNpc(NPC npc, String interactString){
        if (Map.canReach(npc)) {
            npc.interact(interactString);
        }
    }

    public default void interactNpc(NPC npc, String interactString, boolean condition){
        if (npc != null) {
            if (npc.interact(interactString)){
                Sleep.sleepUntil(() -> condition, 5000);
            }
        }
    }

    public default void useItemOn(String first_item, String second_item){
        if (Inventory.interact(first_item, "Use")){
            if (Inventory.interact(second_item, "Use")){
                Sleep.sleep(1,1000);
            }
        }
    }

}
