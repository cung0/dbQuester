package Node.src.Objects;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.interact.Interactable;

public interface Interact extends GameObj, GroundObj, Npc{
    public default boolean interactable(Interactable interactable, String interactString){
        if (interactable != null){
            interactable.interact(interactString);
            return true;
        }
        return false;
    }

    public default boolean interactable(Interactable interactable, String interactString, boolean condition){
        if (interactable != null){
            if (interactable.interact(interactString)){
                Sleep.sleepUntil(() -> condition, 5000);
                return true;
            }
        }
        return false;
    }

    public default void useItemOn(String first_item, String second_item){
        if (Inventory.interact(first_item, "Use")){
            if (Inventory.interact(second_item, "Use")){
                Sleep.sleep(1,1000);
            }
        }
    }

}
