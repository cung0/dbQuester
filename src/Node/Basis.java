package Node.src.Node;

import Action.Dialogue;
import Action.Walk;
import Locations.Contains;
import Objects.GameObj;
import Objects.GroundObj;
import Objects.Npc;
import PlayerSettings.QuestProgress;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public interface Basis extends Walk, GameObj, GroundObj, Dialogue, Npc, QuestProgress, Contains {
    static WidgetChild QUEST_END_SCREEN = Widgets.get(153,16);
    default boolean CLOSE_QUEST_END_SCREEN(boolean quest){
        if (QUEST_END_SCREEN != null){
            QUEST_END_SCREEN.interact("Close");
            return true;
        } else {
            return false;
        }
    }
}
