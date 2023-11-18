package Node.src.Action;

import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public interface Widget {
    default void INTERACT_WIDGET(WidgetChild widget, String interaction, boolean condition){
        if (widget != null){
            if (widget.interact(interaction)){
                Sleep.sleepUntil(() -> condition, 5000);
            }
        }
    }

    default void INTERACT_WIDGET(WidgetChild widget, String interaction, boolean condition, int sleepTime){
        if (widget != null){
            if (widget.interact(interaction)){
                Sleep.sleepUntil(() -> condition, sleepTime);
            }
        }
    }

    default void INTERACT_WIDGET(WidgetChild widget, String interaction){
        if (widget != null){
            widget.interact(interaction);
        }
    }
}
